package it.luca.spring.data.model.conduzione;

import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.PojoValidation;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.model.common.SourceSpecificationFactory;
import org.springframework.stereotype.Component;

@Component
public class ConduzioneSpecificationFactory extends SourceSpecificationFactory<ConduzionePayload> {

    @Override
    protected SourceSpecification<ConduzionePayload> createInstance() {

        PojoValidation<ConduzionePayload> validation = PojoValidation.<ConduzionePayload>builder()
                .withNotNullOrEmptyListCheck("records", ConduzionePayload::getRecords)
                .build();

        return SourceSpecification.<ConduzionePayload>builder()
                .dataSourceId("CONDUZIONE")
                .dataSourceType(DataSourceType.JSON)
                .inputDataClass(ConduzionePayload.class)
                .pojoValidation(validation)
                .build();
    }
}
