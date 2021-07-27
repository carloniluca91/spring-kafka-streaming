package it.luca.spring.data.model.jarvis;

import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.PojoValidation;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.model.common.SourceSpecificationFactory;
import org.springframework.stereotype.Component;

@Component
public class JarvisSpecificationFactory extends SourceSpecificationFactory<JarvisPayload> {

    @Override
    protected SourceSpecification<JarvisPayload> createInstance() {

        PojoValidation<JarvisPayload> validation = PojoValidation.<JarvisPayload>builder()
                .withNotNullAttributeCheck("giornoGas", JarvisPayload::getGiornoGas)
                .withNotNullOrEmptyListCheck("listaCicli", JarvisPayload::getListaCicli)
                .build();

        return SourceSpecification.<JarvisPayload>builder()
                .dataSourceId("JARVIS")
                .dataSourceType(DataSourceType.XML)
                .inputDataClass(JarvisPayload.class)
                .pojoValidation(validation)
                .build();
    }
}
