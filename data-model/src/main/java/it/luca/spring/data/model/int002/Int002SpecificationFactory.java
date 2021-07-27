package it.luca.spring.data.model.int002;

import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.PojoValidation;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.model.common.SourceSpecificationFactory;
import org.springframework.stereotype.Component;

@Component
public class Int002SpecificationFactory extends SourceSpecificationFactory<Int002Payload> {

    @Override
    protected SourceSpecification<Int002Payload> createInstance() {

        PojoValidation<Int002Payload> validation = PojoValidation.<Int002Payload>builder()
                .withNotNullOrEmptyListCheck("cicli", Int002Payload::getCicli)
                .build();

        return SourceSpecification.<Int002Payload>builder()
                .dataSourceId("INT002")
                .dataSourceType(DataSourceType.JSON)
                .inputDataClass(Int002Payload.class)
                .pojoValidation(validation)
                .build();
    }
}
