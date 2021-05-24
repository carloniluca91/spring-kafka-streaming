package it.luca.spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.model.common.MsgWrapper;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.model.validation.common.ValidationDto;
import it.luca.spring.exception.EmptyInputException;
import it.luca.spring.exception.InputValidationException;
import it.luca.spring.jdbc.dao.ApplicationDao;
import it.luca.spring.jdbc.dto.ErrorRecord;
import it.luca.spring.kafka.KafkaProducer;
import it.luca.spring.model.response.DataSourceResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

import static it.luca.spring.data.utils.ObjectDeserializer.readValue;

@Slf4j
@Service
public class PublishService {

    @Autowired
    private KafkaProducer producer;

    @Autowired
    private ApplicationDao dao;

    public <T> DataSourceResponseDto send(String input, SourceSpecification<T> specification) {

        DataSourceId dataSourceId = specification.getDataSourceId();
        Predicate<String> emptyOrBlank = s -> s.isEmpty() | s.isBlank();
        try {
            if (!emptyOrBlank.test(input)) {
                log.info("({}) Received call. Input:\n\n{}\n", dataSourceId, input);
                T payload = readValue(input, specification);
                ValidationDto validationDto = specification.validate(payload);
                if (validationDto.isValid()) {
                    producer.sendMessage(specification, new MsgWrapper<>(payload), dao);
                    return new DataSourceResponseDto(specification, null);
                } else {
                    throw new InputValidationException(validationDto);
                }
            } else {
                throw new EmptyInputException();
            }
        } catch (Exception exception) {
            String errorMsg = ((exception instanceof JsonProcessingException) |
                    (exception instanceof EmptyInputException) |
                    (exception instanceof InputValidationException)) ?
                    "({}) Caught exception while processing received data. Class: {}. Message: {}" :
                    "({}) Caught exception while sending data to Kafka. Class: {}. Message: {}";
            log.error(errorMsg, dataSourceId, exception.getClass().getName(), exception.getMessage());
            dao.insertIngestionRecord(new ErrorRecord(specification, exception));
            return new DataSourceResponseDto(specification, exception);
        }
    }
}
