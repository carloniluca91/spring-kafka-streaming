package it.luca.spring.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.model.common.MsgWrapper;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.exception.EmptyInputException;
import it.luca.spring.jdbc.dao.ApplicationDao;
import it.luca.spring.jdbc.dto.ErrorRecord;
import it.luca.spring.jdbc.dto.SuccessRecord;
import it.luca.spring.kafka.KafkaProducer;
import it.luca.spring.model.dto.SentMessageDto;
import it.luca.spring.model.response.SourceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static it.luca.spring.data.utils.ObjectDeserializer.readValue;

@Slf4j
@Service
public class PublishService {

    @Autowired
    private KafkaProducer kafkaProducer;

    @Autowired
    private ApplicationDao applicationDao;

    public <T> SourceResponse send(String input, SourceSpecification<T> specification) {

        DataSourceId dataSourceId = specification.getDataSourceId();
        Predicate<String> emptyOrBlank = s -> s.isEmpty() | s.isBlank();
        try {
            if (!emptyOrBlank.test(input)) {
                log.info("({}) Received call. Input:\n\n{}\n", dataSourceId, input);
                T payload = readValue(input, specification);
                Optional<SentMessageDto> optionalSentMessageDto = kafkaProducer.sendMessage(specification, new MsgWrapper<>(payload));
                optionalSentMessageDto.ifPresent(x -> writeSuccessRecord(specification, x));
                return new SourceResponse(dataSourceId);
            } else {
                throw new EmptyInputException(dataSourceId);
            }
        } catch (Exception exception) {
            String errorMsg = ((exception instanceof JsonProcessingException) | (exception instanceof EmptyInputException)) ?
                    "({}) Caught exception while processing received data. Class: {}. Message: {}" :
                    "({}) Caught exception while sending data to Kafka. Class: {}. Message: {}";
            log.error(errorMsg, dataSourceId, exception.getClass().getName(), exception.getMessage());
            writeErrorRecord(specification, exception);
            return new SourceResponse(dataSourceId, exception);
        }
    }

    private <T, I> void writeRecord(SourceSpecification<?> specification,
                                    Class<T> tClass,
                                    Function<I, T> function,
                                    I input,
                                    Consumer<T> consumer) {

        DataSourceId dataSourceId = specification.getDataSourceId();
        String recordClassName = tClass.getSimpleName();
        try {
            T t = function.apply(input);
            consumer.accept(t);
        } catch (Exception e) {
            log.error("({}) Caught exception while saving {}. Class: {}. Message: {}",
                    dataSourceId, recordClassName, e.getClass().getName(), e.getMessage());
        }
    }

    private void writeSuccessRecord(SourceSpecification<?> specification, SentMessageDto sentMessageDto) {

        writeRecord(specification,
                SuccessRecord.class,
                x -> new SuccessRecord(specification, x),
                sentMessageDto,
                x -> applicationDao.insertIngestionRecord(x));
    }

    private void writeErrorRecord(SourceSpecification<?> specification, Exception exception) {

        writeRecord(specification,
                ErrorRecord.class,
                x -> new ErrorRecord(specification, x),
                exception,
                x -> applicationDao.insertIngestionRecord(x));
    }
}
