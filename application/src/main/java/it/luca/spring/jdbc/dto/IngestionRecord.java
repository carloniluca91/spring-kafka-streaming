package it.luca.spring.jdbc.dto;

import it.luca.spring.data.enumeration.IngestionOperationCode;
import it.luca.spring.data.model.common.SourceSpecification;
import lombok.Data;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.sql.Date;
import java.sql.Timestamp;

import static it.luca.utils.functional.Optional.*;
import static it.luca.utils.time.Supplier.now;

/**
 * Dto representing record stored on log table
 */

@Data
public abstract class IngestionRecord {

    protected final Timestamp messageTs;
    protected final Date messageDt;
    protected final String dataSourceId;
    protected final String dataSourceType;
    protected final String inputDataClass;
    protected final String topicName;
    protected final Integer topicPartition;
    protected final String ingestionOperationCode;
    protected final Long messageOffset;
    protected final String exceptionClass;
    protected final String exceptionMessage;
    protected final Timestamp insertTs = Timestamp.valueOf(now());
    protected final Date insertDt = Date.valueOf(now().toLocalDate());

    public IngestionRecord(SourceSpecification<?> specification,
                           RecordMetadata recordMetadata,
                           Exception exception) {

        messageTs = orElse(recordMetadata, x -> new Timestamp(x.timestamp()), Timestamp.valueOf(now()));
        messageDt = orElse(recordMetadata, x -> new Date(x.timestamp()), Date.valueOf(now().toLocalDate()));
        dataSourceId = specification.getDataSourceId().name();
        dataSourceType = specification.getDataSourceType().name();
        inputDataClass = specification.getInputDataClass().getName();
        topicName = specification.getTopicName();
        topicPartition = orNull(recordMetadata, RecordMetadata::partition);
        ingestionOperationCode = (isPresent(exception) ? IngestionOperationCode.KO : IngestionOperationCode.OK).name();
        messageOffset = orNull(recordMetadata, RecordMetadata::offset);
        exceptionClass = orNull(exception, x -> x.getClass().getName());
        exceptionMessage = orNull(exception, Throwable::getMessage);
    }
}
