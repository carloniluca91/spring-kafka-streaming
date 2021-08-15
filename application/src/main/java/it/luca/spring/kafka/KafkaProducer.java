package it.luca.spring.kafka;

import it.luca.spring.data.model.common.MsgWrapper;
import it.luca.spring.data.model.common.DataSourceSpecification;
import it.luca.spring.jdbc.dao.ApplicationDao;
import it.luca.spring.jdbc.dto.ErrorRecord;
import it.luca.spring.jdbc.dto.SuccessRecord;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
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
    private KafkaTemplate<String, MsgWrapper<?>> template;

    /**
     * Publish data to Kafka topic
     * @param msgWrapper wrapper object containing data to be sent
     * @param specification dataSource specification
     * @param dao dao interface to be used for logging
     */

    public void sendMessage(MsgWrapper<?> msgWrapper, DataSourceSpecification<?> specification, ApplicationDao dao) {

        String dataSourceId = specification.getId();
        ListenableFuture<SendResult<String, MsgWrapper<?>>> future = template.send(specification.getTopic(), msgWrapper);
        future.addCallback(new ListenableFutureCallback<SendResult<String, MsgWrapper<?>>>() {

            @Override
            @SuppressWarnings("NullableProblems")
            public void onFailure(Throwable throwable) {

                log.error("({}) Unable to send message due to: {}", dataSourceId, throwable.getMessage());
                dao.insertIngestionRecord(new ErrorRecord(specification, (Exception) throwable));
            }

            @Override
            public void onSuccess(SendResult<String, MsgWrapper<?>> sendResult) {

                RecordMetadata recordMetadata = sendResult.getRecordMetadata();
                log.info("({}) Sent message with offset {} to topic partition [{}, {}]",
                        dataSourceId, recordMetadata.offset(), recordMetadata.topic(), recordMetadata.partition());
                dao.insertIngestionRecord(new SuccessRecord(specification, recordMetadata));
            }});
    }
}
