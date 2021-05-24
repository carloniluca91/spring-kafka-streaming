package it.luca.spring.jdbc.dto;

import it.luca.spring.data.model.common.SourceSpecification;

/**
 * Dto representing error records stored on log table
 */

public class ErrorRecord extends IngestionRecord {

    public ErrorRecord(SourceSpecification<?> specification, Exception exception) {

        super(specification, null, exception);
    }
}
