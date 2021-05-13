package it.luca.spring.kafka;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.model.dto.SentMessageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Slf4j
@Component
public class KafkaProducer {

    @Autowired
    private KafkaProducerFactory producerFactory;

    public <A extends SpecificRecord> List<SentMessageDto> sendMessages(SourceSpecification<?, A> specification, List<A> avroRecords) {

        String topicName = specification.getTopicName();
        DataSourceId dataSourceId = specification.getDataSourceId();
        KafkaTemplate<String, A> kafkaTemplate = producerFactory.getKafkaTemplate();
        List<SentMessageDto> sentMessageDtos = new ArrayList<>();
        IntStream.range(0, avroRecords.size()).forEach(i -> {

            A avroRecord = avroRecords.get(i);
            ListenableFuture<SendResult<String, A>> future = kafkaTemplate.send(topicName, avroRecord);
            future.addCallback(new ListenableFutureCallback<>() {

                @Override
                @SuppressWarnings("NullableProblems")
                public void onFailure(Throwable throwable) {
                    log.error("({}) Unable to send message due to: {}", dataSourceId, throwable.getMessage());
                }

                @Override
                public void onSuccess(SendResult<String, A> sendResult) {

                    String topicName = sendResult.getRecordMetadata().topic();
                    int topicPartition = sendResult.getRecordMetadata().partition();
                    long messageOffset = sendResult.getRecordMetadata().offset();
                    sentMessageDtos.add(new SentMessageDto(avroRecords.size(), i + 1, topicPartition, messageOffset));
                    log.info("({}) Sent message with offset {} to topic partition [{}, {}]", dataSourceId, messageOffset, topicName, topicPartition);
                }});
        });

        return sentMessageDtos;
    }
}
