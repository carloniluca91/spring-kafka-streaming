package it.luca.cedacri.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.luca.cedacri.enumeration.DataSourceId;
import it.luca.cedacri.exception.EmptyInputException;
import it.luca.cedacri.json.core.MsgBody;
import it.luca.cedacri.jdbc.core.ImpalaDao;
import it.luca.cedacri.jdbc.core.SaveDao;
import it.luca.cedacri.kafka.KafkaProducer;
import it.luca.cedacri.json.core.JsonObjectMapper;
import it.luca.cedacri.json.core.MsgWrapper;
import it.luca.cedacri.model.response.SourceResponse;
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
    private ImpalaDao impalaDao;

    public <T extends MsgBody, D extends SaveDao<T>> SourceResponse processAndSend(
            String input, DataSourceId dataSourceId, Class<T> tClass, Class<D> dClass) {

        T payload;
        MsgWrapper<T> msgWrapper = null;
        try {
            if (!input.isBlank()) {
                log.info("({}) Received call. Input:\n\n{}\n", dataSourceId.getId(), input);
                payload = JsonObjectMapper.readValue(input, tClass);
                msgWrapper = new MsgWrapper<>(dataSourceId, payload);
                kafkaProducer.sendMessage(msgWrapper);
                return new SourceResponse(dataSourceId, Optional.empty());
            } else {
                throw new EmptyInputException();
            }
        } catch (JsonProcessingException | EmptyInputException exception) {
            log.error("({}) Caught exception while processing received data. Class: {}. Message: {}",
                    dataSourceId.getId(), exception.getClass().getName(), exception.getMessage());
            return new SourceResponse(dataSourceId, Optional.of(exception));
        } catch (KafkaException kafkaException) {
            log.error("({}) Caught exception while sending data. Class: {}. Message: {}",
                    dataSourceId.getId(), kafkaException.getClass(), kafkaException.getMessage());

            // If msgWrapper has been created, save it on application DB
            if (Optional.ofNullable(msgWrapper).isPresent()) {
                impalaDao.save(msgWrapper, dClass);
            }
            return new SourceResponse(dataSourceId, Optional.of(kafkaException));
        }
    }
}
