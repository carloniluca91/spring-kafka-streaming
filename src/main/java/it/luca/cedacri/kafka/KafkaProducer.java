package it.luca.cedacri.kafka;

import it.luca.cedacri.enumeration.DataSourceId;
import it.luca.cedacri.json.core.MsgWrapper;
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
    private KafkaTopics kafkaTopics;

    @Autowired
    private KafkaTemplate<String, MsgWrapper<?>> kafkaTemplate;

    public <T> void sendMessage(MsgWrapper<T> msgWrapper) {

        DataSourceId dataSourceId = msgWrapper.getDataSourceId();
        String topicName = kafkaTopics.getTopicForDataSource(dataSourceId);
        ListenableFuture<SendResult<String, MsgWrapper<?>>> future = kafkaTemplate.send(topicName, msgWrapper);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onFailure(Throwable throwable) {
                log.error("({}) Unable to send message due to: {}", dataSourceId, throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, MsgWrapper<?>> stringTSendResult) {
                log.info("({}) Sent message = [{}] with offset=[{}] to topic {}",
                        dataSourceId, msgWrapper.getPayload(), stringTSendResult.getRecordMetadata().offset(), topicName);
            }
        });
    }
}
