package it.luca.spring.jdbc.dto;

import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.model.dto.SentMessageDto;

/**
 * Dto representing success records stored on log table
 */

public class SuccessRecord extends IngestionRecord {

    public SuccessRecord(SourceSpecification<?> specification, SentMessageDto sentMessageDto) {

        super(specification, sentMessageDto, null);
    }
}
