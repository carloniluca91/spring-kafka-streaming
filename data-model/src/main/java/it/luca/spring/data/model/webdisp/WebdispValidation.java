package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.validation.common.PojoValidation;
import it.luca.spring.data.model.validation.rules.NotEmptyListRule;
import it.luca.spring.data.model.validation.rules.NotNullAttributeRule;

public class WebdispValidation extends PojoValidation<WebdispPayload> {

    public WebdispValidation() {

        super(new NotNullAttributeRule<>(WebdispPayload::getDataOraInvio, "dataOraInvio"),
                new NotEmptyListRule<>(WebdispPayload::getNomine, "nomine"));
    }
}
