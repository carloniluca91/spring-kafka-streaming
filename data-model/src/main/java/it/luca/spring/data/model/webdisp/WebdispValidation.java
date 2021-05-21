package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.validation.common.ObjectValidation;
import it.luca.spring.data.model.validation.rules.NonEmptyListValidation;
import it.luca.spring.data.model.validation.rules.NotNullValidation;
import it.luca.spring.data.utils.XMLField;

public class WebdispValidation extends ObjectValidation<WebdispPayload> {

    public WebdispValidation() {

        super(new NotNullValidation<>(WebdispPayload::getDataOraInvio, XMLField.DATA_ORA_INVIO),
                new NotNullValidation<>(WebdispPayload::getNomine, XMLField.NOMINE),
                new NonEmptyListValidation<>(WebdispPayload::getNomine, XMLField.NOMINE));
    }
}
