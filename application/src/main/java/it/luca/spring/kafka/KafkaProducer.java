package it.luca.spring.kafka;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.model.common.MsgWrapper;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.jdbc.dao.ApplicationDao;
import it.luca.spring.jdbc.dto.ErrorRecord;
import it.luca.spring.jdbc.dto.SuccessRecord;
import it.luca.spring.model.dto.SentMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, MsgWrapper<?>> kafkaTemplate;

    public void sendMessage(SourceSpecification<?> specification, MsgWrapper<?> msgWrapper, ApplicationDao dao) {

        String topic = specification.getTopicName();
        DataSourceId dataSourceId = specification.getDataSourceId();
        ListenableFuture<SendResult<String, MsgWrapper<?>>> future = kafkaTemplate.send(topic, msgWrapper);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            @SuppressWarnings("NullableProblems")
            public void onFailure(Throwable throwable) {

                log.error("({}) Unable to send message due to: {}", dataSourceId, throwable.getMessage());
                dao.insertIngestionRecord(new ErrorRecord(specification, (Exception) throwable));

            }

            @Override
            public void onSuccess(SendResult<String, MsgWrapper<?>> sendResult) {

                int topicPartition = sendResult.getRecordMetadata().partition();
                long messageOffset = sendResult.getRecordMetadata().offset();
                log.info("({}) Sent message with offset {} to topic partition [{}, {}]", dataSourceId, messageOffset, topic, topicPartition);
                dao.insertIngestionRecord(new SuccessRecord(specification, new SentMessageDto(topicPartition, messageOffset)));
            }});
    }
}
