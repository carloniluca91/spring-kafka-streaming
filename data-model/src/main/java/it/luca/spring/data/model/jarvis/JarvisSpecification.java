package it.luca.spring.data.model.jarvis;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.SourceSpecification;

public class JarvisSpecification extends SourceSpecification<JarvisPayload> {

    public JarvisSpecification(String topicName) {
        super(DataSourceId.JARVIS, DataSourceType.XML, JarvisPayload.class, new JarvisValidation(), topicName);
    }
}
