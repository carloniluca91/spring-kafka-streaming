package it.luca.spring.model.dto;

import lombok.Data;

/**
 * Dto containing details about messages published to a Kafka topic
 */

@Data
public class SentMessageDto {

    private final int topicPartition;
    private final long messageOffset;
}
