package it.luca.spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.exception.EmptyInputException;
import it.luca.spring.jdbc.dao.ApplicationDao;
import it.luca.spring.kafka.KafkaProducer;
import it.luca.spring.jdbc.bean.IngestionErrorRecord;
import it.luca.spring.model.response.SourceResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import static it.luca.spring.data.utils.ObjectDeserializer.readValue;

@Slf4j
@Service
public class SenderService {

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
                //kafkaProducer.sendMessages(specification, avroRecords);
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

    private void writeErrorRecord(SourceSpecification<?, ?> specification, Exception exception) {

        try {
            IngestionErrorRecord ingestionAlertRecord = new IngestionErrorRecord(specification, exception);
            applicationDao.insertRecord(ingestionAlertRecord);
        } catch (Exception e) {
            log.error("({}) Caught exception while saving alert record. Class: {}. Message: {}",
                    specification.getDataSourceId(), e.getClass().getName(), e.getMessage());
        }
    }
}
