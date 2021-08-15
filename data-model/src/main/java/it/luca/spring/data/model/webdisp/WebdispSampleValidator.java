package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.common.PojoValidation;
import it.luca.spring.data.model.common.SampleValidator;

public class WebdispSampleValidator implements SampleValidator<WebdispPayload> {

    @Override
    public PojoValidation<WebdispPayload> create() {

        return  PojoValidation.<WebdispPayload>builder()
                .withNotNullAttributeCheck("dataOraInvio", WebdispPayload::getDataOraInvio)
                .withNotNullOrEmptyListCheck("nomine", WebdispPayload::getNomine)
                .build();
    }
}
