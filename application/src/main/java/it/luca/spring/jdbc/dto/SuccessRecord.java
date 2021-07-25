package it.luca.spring.jdbc.dto;

import it.luca.spring.data.model.common.SourceSpecification;
import org.apache.kafka.clients.producer.RecordMetadata;

/**
 * Dto representing success records stored on log table
 */

public class SuccessRecord extends IngestionRecord {

    public SuccessRecord(SourceSpecification<?> specification, RecordMetadata recordMetadata) {

        super(specification, recordMetadata, null);
    }
}
