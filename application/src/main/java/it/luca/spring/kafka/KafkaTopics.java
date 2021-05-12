package it.luca.spring.kafka;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaTopics {

    @Value("${topic.conduzione}")
    private String conduzioneTopic;

    @Value("${topic.jarvis}")
    private String jarvisTopic;

    public String getTopicForDataSource(DataSourceId dataSourceId) {

        String topic;
        switch (dataSourceId) {
            case CONDUZIONE: topic = conduzioneTopic; break;
            case JARVIS: topic = jarvisTopic; break;
            default: throw new IllegalStateException("Unexpected dataSource: " + dataSourceId);
        }

        return topic;
    }
}
