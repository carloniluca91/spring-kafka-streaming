package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.validation.common.ObjectValidation;
import it.luca.spring.data.model.validation.rules.NotEmptyListRule;
import it.luca.spring.data.model.validation.rules.NotNullRule;

public class WebdispValidation extends ObjectValidation<WebdispPayload> {

    public WebdispValidation() {

        super(new NotNullRule<>(WebdispPayload::getDataOraInvio, "dataOraInvio"),
                new NotEmptyListRule<>(WebdispPayload::getNomine, "nomine"));
    }
}
