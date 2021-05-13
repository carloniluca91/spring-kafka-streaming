package it.luca.spring.kafka;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class KafkaProducerFactory {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.producer.acks}")
    private String acks;

    @Value("${spring.kafka.producer.retries}")
    private int retries;

    @Value("${spring.kafka.producer.properties.delivery-timeout-ms}")
    private int deliveryTimeoutMs;

    @Value("${spring.kafka.producer.properties.max-block-ms}")
    private int maxBlockMs;

    @Value("${spring.kafka.producer.properties.request-timeout-ms}")
    private int requestTimeoutMs;

    @Value("${spring.kafka.producer.properties.reconnect-backoff-ms}")
    private int reconnectBackoffMs;

    @Value("${spring.kafka.producer.properties.retry-backoff-ms}")
    private int retryBackoffMs;

    @Value("${spring.kafka.producer.properties.linger-ms}")
    private int lingerMs;

    @Bean
    public Map<String, Object> producerConfigs() {

        return new HashMap<>() {{

            put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
            put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
            put(ProducerConfig.ACKS_CONFIG, acks);
            put(ProducerConfig.RETRIES_CONFIG, retries);
            put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, deliveryTimeoutMs);
            put(ProducerConfig.MAX_BLOCK_MS_CONFIG, maxBlockMs);
            put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, requestTimeoutMs);
            put(ProducerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, reconnectBackoffMs);
            put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, retryBackoffMs);
            put(ProducerConfig.LINGER_MS_CONFIG, lingerMs);
        }};
    }

    @Bean
    public <A extends SpecificRecord> ProducerFactory<String, A> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public <A extends SpecificRecord> KafkaTemplate<String, A> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
