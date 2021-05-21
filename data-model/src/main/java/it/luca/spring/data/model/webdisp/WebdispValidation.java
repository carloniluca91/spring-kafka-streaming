package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.validation.EmptyListDescription;
import it.luca.spring.data.model.validation.NullAttributeDescription;
import it.luca.spring.data.model.validation.ObjectValidation;
import it.luca.spring.data.model.validation.ValidationRule;
import it.luca.spring.data.utils.XMLField;

import static it.luca.spring.data.utils.Utils.isPresent;

public class WebdispValidation extends ObjectValidation<WebdispPayload> {

    public WebdispValidation() {
        super(new ValidationRule<>(x -> isPresent(x.getDataOraInvio()), new NullAttributeDescription(XMLField.DATA_ORA_INVIO)),
                new ValidationRule<>(x -> isPresent(x.getNomine()), new NullAttributeDescription(XMLField.NOMINE)),
                new ValidationRule<>(x -> !x.getNomine().isEmpty(), new EmptyListDescription(XMLField.NOMINE)));
    }
}
