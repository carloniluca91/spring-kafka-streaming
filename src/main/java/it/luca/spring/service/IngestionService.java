package it.luca.spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.luca.spring.enumeration.DataSourceId;
import it.luca.spring.exception.EmptyInputException;
import it.luca.spring.jdbc.core.ApplicationDao;
import it.luca.spring.jdbc.core.GenericDao;
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

    public <T, D extends GenericDao<T>> SourceResponse processAndSend(String input, DataSourceId dataSourceId, Class<T> tClass, Class<D> dClass) {

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
            return new SourceResponse(dataSourceId, Optional.of(exception));
        } catch (KafkaException kafkaException) {
            log.error("({}) Caught exception while sending data. Class: {}. Message: {}",
                    dataSourceId, kafkaException.getClass(), kafkaException.getMessage());
            return insertRecord(dataSourceId, payload, dClass);
        }
    }

    private <T, D extends GenericDao<T>> SourceResponse insertRecord(DataSourceId dataSourceId, T payload, Class<D> dClass) {

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
