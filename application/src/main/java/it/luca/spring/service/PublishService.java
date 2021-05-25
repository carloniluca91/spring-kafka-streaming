package it.luca.spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.model.common.MsgWrapper;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.model.validation.common.ObjectValidationDto;
import it.luca.spring.exception.EmptyInputException;
import it.luca.spring.exception.InputValidationException;
import it.luca.spring.jdbc.dao.ApplicationDao;
import it.luca.spring.jdbc.dto.ErrorRecord;
import it.luca.spring.kafka.KafkaProducer;
import it.luca.spring.model.response.DataSourceResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;
import java.util.function.Predicate;

import static it.luca.spring.data.utils.ObjectDeserializer.readValue;

@Slf4j
@Service
public class PublishService {

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private ApplicationDao dao;

    /**
     * Deserializes input data and publish them to a Kafka topic
     * @param input string representing serialized input data
     * @param specification dataSource specification
     * @param <T> type to be used for deserialization
     * @return dto to be sent back to dataSources
     */

    public <T> DataSourceResponseDto send(String input, SourceSpecification<T> specification) {

        DataSourceId dataSourceId = specification.getDataSourceId();
        Predicate<String> emptyOrBlank = s -> s.isEmpty() || s.isBlank();
        BiFunction<HttpStatus, Exception, DataSourceResponseDto> dtoFunction = (status, exception) ->
                new DataSourceResponseDto(specification, status, exception);
        try {

            // If input string is not empty or blank
            if (!emptyOrBlank.test(input)) {

                // Deserialize it and validate its content
                log.info("({}) Received call. Input:\n\n{}\n", dataSourceId, input);
                T payload = readValue(input, specification);
                ObjectValidationDto objectValidtion = specification.validate(payload);

                // If validation successes, publish on Kafka
                if (objectValidtion.isValid()) {
                    producer.sendMessage(specification, new MsgWrapper<>(payload), dao);
                    return dtoFunction.apply(HttpStatus.OK, null);
                } else {
                    throw new InputValidationException(objectValidtion);
                }
            } else {
                throw new EmptyInputException();
            }
        } catch (Exception exception) {

            // Set HttpStatus and error message according to exception type
            String errorMsg;
            HttpStatus status;
            if ((exception instanceof JsonProcessingException) ||
                    (exception instanceof EmptyInputException) ||
                    (exception instanceof InputValidationException)) {

                errorMsg = "({}) Caught exception while processing received data. Class: {}. Message: {}";
                status = HttpStatus.UNPROCESSABLE_ENTITY;
            } else {

                errorMsg = "({}) Caught exception while publishing data. Class: {}. Message: {}";
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }

            log.error(errorMsg, dataSourceId, exception.getClass().getName(), exception.getMessage());
            dao.insertIngestionRecord(new ErrorRecord(specification, exception));
            return dtoFunction.apply(status, exception);
        }
    }
}
