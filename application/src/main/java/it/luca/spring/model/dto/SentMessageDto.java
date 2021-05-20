package it.luca.spring.model.dto;

import lombok.Data;

@Data
public class SentMessageDto {

    private final int topicPartition;
    private final long messageOffset;
}
