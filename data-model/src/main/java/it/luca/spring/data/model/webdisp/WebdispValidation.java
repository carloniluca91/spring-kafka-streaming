package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.validation.common.ObjectValidation;
import it.luca.spring.data.model.validation.rules.NotEmptyListRule;
import it.luca.spring.data.model.validation.rules.NotNullRule;
import it.luca.spring.data.utils.XMLField;

public class WebdispValidation extends ObjectValidation<WebdispPayload> {

    public WebdispValidation() {

        super(new NotNullRule<>(WebdispPayload::getDataOraInvio, XMLField.DATA_ORA_INVIO),
                new NotEmptyListRule<>(WebdispPayload::getNomine, XMLField.NOMINE));
    }
}
