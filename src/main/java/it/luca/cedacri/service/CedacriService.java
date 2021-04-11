package it.luca.cedacri.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.luca.cedacri.enumeration.DataSourceId;
import it.luca.cedacri.exception.EmptyInputException;
import it.luca.cedacri.jdbc.core.ApplicationDao;
import it.luca.cedacri.jdbc.core.DataSourceDao;
import it.luca.cedacri.json.core.JsonObjectMapper;
import it.luca.cedacri.json.core.MsgWrapper;
import it.luca.cedacri.kafka.KafkaProducer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.KafkaException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class CedacriService {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private ApplicationDao applicationDao;

    public <T, D extends DataSourceDao<T>> SourceResponse processAndSend(String input, DataSourceId dataSourceId, Class<T> tClass, Class<D> dClass) {

        T payload = null;
        try {
            if (!input.isBlank()) {
                log.info("({}) Received call. Input:\n\n{}\n", dataSourceId, input);
                payload = JsonObjectMapper.readValue(input, tClass);
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
            return insertInDb(dataSourceId, payload, dClass);
        }
    }

    private <T, D extends DataSourceDao<T>> SourceResponse insertInDb(DataSourceId dataSourceId, T payload, Class<D> dClass) {

        SourceResponse sourceResponse;
        try {
            applicationDao.saveUnsentMessage(payload, dClass);
            sourceResponse = new SourceResponse(dataSourceId, Optional.empty());
        } catch (Exception exception) {
            log.error("({}) Caught exception while saving data to DB. Class: {}. Message: {}",
                    dataSourceId, exception.getClass(), exception.getMessage());
            sourceResponse = new SourceResponse(dataSourceId, Optional.of(exception));
        }

        return sourceResponse;
    }
}
