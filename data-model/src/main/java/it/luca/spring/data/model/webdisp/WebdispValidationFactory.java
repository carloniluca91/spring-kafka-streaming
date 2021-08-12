package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.common.PojoValidation;
import it.luca.spring.data.model.common.SourceValidationFactory;

public class WebdispValidationFactory implements SourceValidationFactory<WebdispPayload> {

    @Override
    public PojoValidation<WebdispPayload> create() {

        return  PojoValidation.<WebdispPayload>builder()
                .withNotNullAttributeCheck("dataOraInvio", WebdispPayload::getDataOraInvio)
                .withNotNullOrEmptyListCheck("nomine", WebdispPayload::getNomine)
                .build();
    }
}
