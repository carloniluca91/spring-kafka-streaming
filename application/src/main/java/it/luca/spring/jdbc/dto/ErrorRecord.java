package it.luca.spring.jdbc.dto;

import it.luca.spring.data.model.common.SourceSpecification;

public class ErrorRecord extends IngestionRecord {

    public ErrorRecord(SourceSpecification<?> specification, Exception exception) {

        super(specification, null, exception);
    }
}
