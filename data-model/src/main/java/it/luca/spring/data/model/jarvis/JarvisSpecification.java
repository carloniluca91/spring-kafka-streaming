package it.luca.spring.data.model.jarvis;

import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.SourceSpecification;

public class JarvisSpecification extends SourceSpecification<JarvisPayload> {

    public JarvisSpecification() {

        super(JarvisPayload.class, DataSourceType.XML, new JarvisValidation());
    }
}
