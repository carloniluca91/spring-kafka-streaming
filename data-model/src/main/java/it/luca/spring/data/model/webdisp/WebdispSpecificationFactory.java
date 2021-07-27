package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.model.common.SourceSpecificationFactory;
import it.luca.spring.data.model.common.PojoValidation;
import org.springframework.stereotype.Component;

@Component
public class WebdispSpecificationFactory extends SourceSpecificationFactory<WebdispPayload> {

    @Override
    protected SourceSpecification<WebdispPayload> createInstance() {

        PojoValidation<WebdispPayload> validation2 = PojoValidation.<WebdispPayload>builder()
                .withNotNullAttributeCheck("dataOraInvio", WebdispPayload::getDataOraInvio)
                .withNotNullOrEmptyListCheck("nomine", WebdispPayload::getNomine)
                .build();

        return SourceSpecification.<WebdispPayload>builder()
                .dataSourceId("WEBDISP")
                .dataSourceType(DataSourceType.XML)
                .inputDataClass(WebdispPayload.class)
                .pojoValidation(validation2)
                .build();
    }
}
