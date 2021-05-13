package it.luca.spring.kafka;

import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaProducerFactory {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.retries}")
    private int retries;

    @Value("${spring.kafka.producer.delivery-timeout-ms}")
    private int deliveryTimeoutMs;

    @Value("${spring.kafka.producer.max-block-ms}")
    private int maxBlockMs;

    @Value("${spring.kafka.producer.request-timeout-ms}")
    private int requestTimeoutMs;

    @Value("${spring.kafka.producer.reconnect-backoff-ms}")
    private int reconnectBackoffMs;

    @Value("${spring.kafka.producer.retry-backoff-ms}")
    private int retryBackoffMs;

    @Value("${spring.kafka.producer.linger-ms}")
    private int lingerMs;

    public <A extends SpecificRecord> KafkaTemplate<String, A> getKafkaTemplate() {

        Map<String, Object> producerConfig = new HashMap<>() {{

            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, AvroSerializer.class);
            put(ProducerConfig.ACKS_CONFIG, acks);
            put(ProducerConfig.RETRIES_CONFIG, retries);
            put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeoutMs);
            put(ProducerConfig.MAX_BLOCK_MS_CONFIG, maxBlockMs);
            put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeoutMs);
            put(ProducerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, reconnectBackoffMs);
            put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, retryBackoffMs);
            put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        }};

        DefaultKafkaProducerFactory<String, A> defaultKafkaProducerFactory = new DefaultKafkaProducerFactory<>(producerConfig);
        return new KafkaTemplate<>(defaultKafkaProducerFactory);
    }
}
