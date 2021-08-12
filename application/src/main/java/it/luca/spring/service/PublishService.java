package it.luca.spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.luca.spring.data.model.common.MsgWrapper;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.model.common.SourceSpecification2;
import it.luca.spring.data.model.validation.dto.PojoValidationDto;
import it.luca.spring.exception.EmptyInputException;
import it.luca.spring.exception.InputValidationException;
import it.luca.spring.jdbc.dao.ApplicationDao;
import it.luca.spring.jdbc.dto.ErrorRecord;
import it.luca.spring.kafka.KafkaProducer;
import it.luca.spring.model.DataSourceResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

import static it.luca.spring.data.utils.ObjectDeserializer.deserialize;
import static it.luca.utils.functional.Optional.isPresent;

@Slf4j
@Service
public class PublishService {

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private ApplicationDao dao;

    /**
     * Deserializes input data and publish them to a Kafka topic
     * @param topic name of the Kafka topic
     * @param input string representing serialized input data
     * @param specification dataSource specification
     * @param <T> type to be used for deserialization
     * @return dto to be sent back to dataSources
     */

    public <T> DataSourceResponseDto send(String topic, String input, SourceSpecification<T> specification) {

        String dataSourceId = specification.getDataSourceId();
        Predicate<String> emptyOrBlank = s -> !isPresent(s) || s.isEmpty() || s.trim().isEmpty();
        try {
            // If input string is not empty or blank
            if (emptyOrBlank.negate().test(input)) {

                // Deserialize it and validate its content
                log.info("({}) Received call. Input:\n\n{}\n", dataSourceId, input);
                T payload = deserialize(input, specification);
                PojoValidationDto pojoValidationDto = specification.validate(payload);

                // If validation successes, publish on Kafka
                if (pojoValidationDto.isPojoInstanceValid()) {
                    producer.sendMessage(topic, new MsgWrapper<>(payload), specification, dao);
                    return new DataSourceResponseDto(specification, HttpStatus.OK, null);
                } else {
                    throw new InputValidationException(pojoValidationDto);
                }
            } else {
                throw new EmptyInputException();
            }
        } catch (Exception exception) {
            return handleException(specification, exception);
        }
    }


    /**
     * Handle exception generated by data deserialization and delivery to Kafka
     * @param specification dataSource specification
     * @param exception generated exception
     * @return dto to be sent back to dataSources
     */

    private DataSourceResponseDto handleException(SourceSpecification<?> specification, Exception exception) {

        // Set HttpStatus and error message according to exception type
        String errorMsg;
        HttpStatus status;
        if ((exception instanceof JsonProcessingException) ||
                (exception instanceof EmptyInputException) ||
                (exception instanceof InputValidationException)) {

            errorMsg = "Caught exception while processing received data";
            status = HttpStatus.UNPROCESSABLE_ENTITY;
        } else {

            errorMsg = "Caught exception while delivering data";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        log.error("({}) {}. Class: {}, message: {}", specification.getDataSourceId(), errorMsg, exception.getClass().getName(), exception.getMessage());
        dao.insertIngestionRecord(new ErrorRecord(specification, exception));
        return new DataSourceResponseDto(specification, status, exception);
    }
}
