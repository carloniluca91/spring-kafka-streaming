package it.luca.spring.jdbc.dto;

import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.model.dto.SentMessageDto;
import lombok.Getter;

@Getter
public class SuccessDto extends BaseDto {

    private final int topicPartition;
    private final long messageOffset;
    private final int batchTotalRecords;
    private final int batchRecordProgressive;

    public SuccessDto(SourceSpecification<?, ?> specification, SentMessageDto sentMessageDto) {

        super(specification);
        topicPartition = sentMessageDto.getTopicPartition();
        messageOffset = sentMessageDto.getMessageOffset();
        batchTotalRecords = sentMessageDto.getBatchTotalRecords();
        batchRecordProgressive = sentMessageDto.getBatchRecordProgressive();
    }
}
