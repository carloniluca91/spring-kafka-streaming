package it.luca.spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.luca.spring.enumeration.DataSourceId;
import it.luca.spring.exception.EmptyInputException;
import it.luca.spring.jdbc.bean.IngestionAlertRecord;
import it.luca.spring.jdbc.core.ApplicationDao;
import it.luca.spring.jdbc.core.GenericDao;
import it.luca.spring.jdbc.dao.IngestionAlertDao;
import it.luca.spring.json.core.JsonMapper;
import it.luca.spring.json.core.MsgWrapper;
import it.luca.spring.kafka.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngestionService {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private ApplicationDao applicationDao;

    public <T, D extends GenericDao<T>> SourceResponse save(String input, DataSourceId dataSourceId, Class<T> tClass, Class<D> dClass) {

        T payload = null;
        try {
            if (!input.isBlank()) {
                log.info("({}) Received call. Input:\n\n{}\n", dataSourceId, input);
                payload = JsonMapper.readValue(input, tClass);
                kafkaProducer.sendMessage(new MsgWrapper<>(dataSourceId, payload));
                return new SourceResponse(dataSourceId, Optional.empty());
            } else {
                throw new EmptyInputException();
            }
        } catch (JsonProcessingException | EmptyInputException exception) {
            log.error("({}) Caught exception while processing received data. Class: {}. Message: {}",
                    dataSourceId, exception.getClass().getName(), exception.getMessage());
            insertAlertRecord(dataSourceId, exception);
            return new SourceResponse(dataSourceId, Optional.of(exception));
        } catch (KafkaException kafkaException) {
            log.error("({}) Caught exception while sending data. Class: {}. Message: {}",
                    dataSourceId, kafkaException.getClass(), kafkaException.getMessage());
            insertAlertRecord(dataSourceId, kafkaException);
            return insertDataRecord(dataSourceId, payload, dClass);
        }
    }

    private void insertAlertRecord(DataSourceId dataSourceId, Exception exception) {

        try {
            IngestionAlertRecord ingestionAlertRecord = new IngestionAlertRecord(dataSourceId, exception);
            applicationDao.insertRecord(ingestionAlertRecord, IngestionAlertDao.class);
        } catch (Exception e) {
            log.error("({}) Caught exception while saving alert record. Class: {}. Message: {}",
            dataSourceId, exception.getClass().getName(), exception.getMessage());
        }
    }

    private <T, D extends GenericDao<T>> SourceResponse insertDataRecord(DataSourceId dataSourceId, T payload, Class<D> dClass) {

        SourceResponse sourceResponse;
        try {
            applicationDao.insertRecord(payload, dClass);
            sourceResponse = new SourceResponse(dataSourceId, Optional.empty());
        } catch (Exception exception) {
            log.error("({}) Caught exception while saving data to DB. Class: {}. Message: {}",
                    dataSourceId, exception.getClass(), exception.getMessage());
            sourceResponse = new SourceResponse(dataSourceId, Optional.of(exception));
        }

        return sourceResponse;
    }
}
