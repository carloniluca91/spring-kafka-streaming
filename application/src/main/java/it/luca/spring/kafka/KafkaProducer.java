package it.luca.spring.kafka;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.model.common.SourceSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class KafkaProducer {

    public <A extends SpecificRecord> List<Long> sendMessages(SourceSpecification<?, A> specification, List<A> avroRecords) {

        String topicName = specification.getTopicName();
        DataSourceId dataSourceId = specification.getDataSourceId();
        Map<String, Object> map = new HashMap<>();
        KafkaTemplate<String, A> kafkaTemplate = new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(map));
        avroRecords.forEach(x -> {

            ListenableFuture<SendResult<String, A>> future = kafkaTemplate.send(topicName, x);
            future.addCallback(new ListenableFutureCallback<>() {

                @Override
                @SuppressWarnings("NullableProblems")
                public void onFailure(Throwable throwable) {
                    log.error("({}) Unable to send message due to: {}", dataSourceId, throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, A> stringTSendResult) {
                    log.info("({}) Sent message with offset=[{}] to topic {}",
                            dataSourceId, stringTSendResult.getRecordMetadata().offset(), topicName);
                }});
        });

        return new ArrayList<>();
    }
    /*
    @Autowired
    private KafkaTopics kafkaTopics;

    /*
    public <A extends SpecificRecord> List<Long> sendMessages(SourceSpecification<?, A> specification, List<A> avroRecords) {

        String topicName = specification.getTopicName();
        DataSourceId dataSourceId = specification.getDataSourceId();
        avroRecords.forEach(x -> {

            ListenableFuture<SendResult<String, A>> future = kafkaTemplate.send(topicName, x);
            future.addCallback(new ListenableFutureCallback<>() {

                @Override
                @SuppressWarnings("NullableProblems")
                public void onFailure(Throwable throwable) {
                    log.error("({}) Unable to send message due to: {}", dataSourceId, throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, A> stringTSendResult) {
                    log.info("({}) Sent message with offset=[{}] to topic {}",
                            dataSourceId, stringTSendResult.getRecordMetadata().offset(), topicName);
                }
            });
        });
    }

    /*
    public <T> void sendMessage(T message) {

        String topicName = kafkaTopics.getTopicForDataSource(dataSourceId);
        ListenableFuture<SendResult<String, MsgWrapper<?>>> future = kafkaTemplate.send(topicName, msgWrapper);
        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            @SuppressWarnings("NullableProblems")
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

     */
}
