package it.luca.spring.jdbc.dto;

import it.luca.spring.data.enumeration.IngestionOperationCode;
import it.luca.spring.data.model.common.DataSourceSpecification;
import lombok.Getter;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.sql.Date;
import java.sql.Timestamp;

import static it.luca.utils.functional.Optional.*;
import static it.luca.utils.time.Supplier.now;

/**
 * Dto representing record stored on log table
 */

@Getter
public abstract class IngestionRecord {

    protected final Timestamp eventTs;
    protected final Date eventDt;
    protected final String dataSourceId;
    protected final String dataSourceType;
    protected final String inputDataClass;
    protected final String ingestionOperationCode;
    protected final String topicName;
    protected final Integer topicPartition;
    protected final Long messageOffset;
    protected final String exceptionClass;
    protected final String exceptionMessage;
    protected final Timestamp insertTs = Timestamp.valueOf(now());
    protected final Date insertDt = Date.valueOf(now().toLocalDate());

    public IngestionRecord(DataSourceSpecification<?> specification,
                           RecordMetadata recordMetadata,
                           Exception exception) {

        eventTs = orElse(recordMetadata, x -> new Timestamp(x.timestamp()), Timestamp.valueOf(now()));
        eventDt = orElse(recordMetadata, x -> new Date(x.timestamp()), Date.valueOf(now().toLocalDate()));
        dataSourceId = specification.getId();
        dataSourceType = specification.getType().name();
        inputDataClass = specification.getDataClass().getName();
        ingestionOperationCode = (isPresent(exception) ? IngestionOperationCode.KO : IngestionOperationCode.OK).name();
        topicName = orNull(recordMetadata, RecordMetadata::topic);
        topicPartition = orNull(recordMetadata, RecordMetadata::partition);
        messageOffset = orNull(recordMetadata, RecordMetadata::offset);
        exceptionClass = orNull(exception, x -> x.getClass().getName());
        exceptionMessage = orNull(exception, Throwable::getMessage);
    }
}
