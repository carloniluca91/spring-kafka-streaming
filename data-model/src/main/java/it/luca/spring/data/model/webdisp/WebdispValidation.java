package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.validation.common.PojoValidation;
import it.luca.spring.data.model.validation.check.NotEmptyListCheck;
import it.luca.spring.data.model.validation.check.NotNullAttributeCheck;

public class WebdispValidation extends PojoValidation<WebdispPayload> {

    public WebdispValidation() {

        super(new NotNullAttributeCheck<>(WebdispPayload::getDataOraInvio, "dataOraInvio"),
                new NotEmptyListCheck<>(WebdispPayload::getNomine, "nomine"));
    }
}
