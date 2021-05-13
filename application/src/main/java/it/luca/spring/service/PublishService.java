package it.luca.spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.exception.EmptyInputException;
import it.luca.spring.jdbc.dao.ApplicationDao;
import it.luca.spring.jdbc.dto.ErrorDto;
import it.luca.spring.jdbc.dto.SuccessDto;
import it.luca.spring.kafka.KafkaProducer;
import it.luca.spring.model.dto.SentMessageDto;
import it.luca.spring.model.response.SourceResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static it.luca.spring.data.utils.ObjectDeserializer.readValue;
import static it.luca.spring.data.utils.Utils.map;

@Slf4j
@Service
public class PublishService {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private ApplicationDao applicationDao;

    public <T, A extends SpecificRecord> SourceResponse send(String input, SourceSpecification<T, A> specification) {

        DataSourceId dataSourceId = specification.getDataSourceId();
        Predicate<String> emptyOrBlank = s -> s.isEmpty() | s.isBlank();
        try {
            if (!emptyOrBlank.test(input)) {
                log.info("({}) Received call. Input:\n\n{}\n", dataSourceId, input);
                T payload = readValue(input, specification);
                List<A> avroRecords = specification.getAvroRecords(payload);
                List<SentMessageDto> sentMessageDtos = kafkaProducer.sendMessages(specification, avroRecords);
                writeSuccessRecords(specification, sentMessageDtos);
                return new SourceResponse(dataSourceId, Optional.empty());
            } else {
                throw new EmptyInputException(dataSourceId);
            }
        } catch (Exception exception) {
            String errorMsg = ((exception instanceof JsonProcessingException) | (exception instanceof EmptyInputException)) ?
                    "({}) Caught exception while processing received data. Class: {}. Message: {}" :
                    "({}) Caught exception while sending data to Kafka. Class: {}. Message: {}";
            log.error(errorMsg, dataSourceId, exception.getClass().getName(), exception.getMessage());
            writeErrorRecord(specification, exception);
            return new SourceResponse(dataSourceId, Optional.of(exception));
        }
    }

    private void writeSuccessRecords(SourceSpecification<?, ?> specification, List<SentMessageDto> sentMessageDtos) {

        DataSourceId dataSourceId = specification.getDataSourceId();
        int recordSize = sentMessageDtos.size();
        String recordClassName = SuccessDto.class.getSimpleName();
        try {
            List<SuccessDto> successDtos = map(sentMessageDtos, x -> new SuccessDto(specification, x));
            applicationDao.insertSuccessDtos(successDtos);
        } catch (Exception e) {
            log.error("({}) Caught exception while saving {} {}. Class: {}. Message: {}",
                    dataSourceId, recordSize, recordClassName, e.getClass().getName(), e.getMessage());
        }
    }

    private void writeErrorRecord(SourceSpecification<?, ?> specification, Exception exception) {

        DataSourceId dataSourceId = specification.getDataSourceId();
        String recordClassName = ErrorDto.class.getSimpleName();
        try {
            ErrorDto ingestionAlertRecord = new ErrorDto(specification, exception);
            applicationDao.insertErrorDto(ingestionAlertRecord);
        } catch (Exception e) {
            log.error("({}) Caught exception while saving {}. Class: {}. Message: {}",
                    dataSourceId, recordClassName, e.getClass().getName(), e.getMessage());
        }
    }
}
