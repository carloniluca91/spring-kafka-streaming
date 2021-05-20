package it.luca.spring.kafka;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.model.common.MsgWrapper;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.model.dto.SentMessageDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, MsgWrapper<?>> kafkaTemplate;

    public Optional<SentMessageDto> sendMessage(SourceSpecification<?> specification, MsgWrapper<?> msgWrapper) {

        String topic = specification.getTopicName();
        DataSourceId dataSourceId = specification.getDataSourceId();
        ListenableFuture<SendResult<String, MsgWrapper<?>>> future = kafkaTemplate.send(topic, msgWrapper);
        List<SentMessageDto> sentMessageDtos = new ArrayList<>();
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            @SuppressWarnings("NullableProblems")
            public void onFailure(Throwable throwable) {
                log.error("({}) Unable to send message due to: {}", dataSourceId, throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, MsgWrapper<?>> sendResult) {

                String topicName = sendResult.getRecordMetadata().topic();
                int topicPartition = sendResult.getRecordMetadata().partition();
                long messageOffset = sendResult.getRecordMetadata().offset();
                sentMessageDtos.add(new SentMessageDto(topicPartition, messageOffset));
                log.info("({}) Sent message with offset {} to topic partition [{}, {}]", dataSourceId, messageOffset, topicName, topicPartition);
            }});

        return sentMessageDtos.isEmpty() ?
                Optional.empty() :
                Optional.of(sentMessageDtos.get(0));
    }
}
