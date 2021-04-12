package it.luca.spring.kafka;

import it.luca.spring.enumeration.DataSourceId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopics {

    @Value("${topic.bancll01}")
    private String bancll01Topic;

    @Value("${topic.bancll34}")
    private String bancll34Topic;

    public String getTopicForDataSource(DataSourceId dataSourceId) {

        String topic;
        switch (dataSourceId) {
            case CONDUZIONE: topic = bancll01Topic; break;
            case JARVIS: topic = bancll34Topic; break;
            default: throw new IllegalStateException("Unexpected dataSource: " + dataSourceId);
        }

        return topic;
    }
}
