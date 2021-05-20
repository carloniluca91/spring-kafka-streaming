package it.luca.spring.jdbc.dto;

import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.model.dto.SentMessageDto;

public class SuccessRecord extends IngestionLogRecord {

    public SuccessRecord(SourceSpecification<?> specification, SentMessageDto sentMessageDto) {

        super(specification, sentMessageDto, null);
    }
}
