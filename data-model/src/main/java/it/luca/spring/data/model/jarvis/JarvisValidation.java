package it.luca.spring.data.model.jarvis;

import it.luca.spring.data.model.validation.common.PojoValidation;
import it.luca.spring.data.model.validation.rules.NotEmptyListRule;
import it.luca.spring.data.model.validation.rules.NotNullAttributeRule;

public class JarvisValidation extends PojoValidation<JarvisPayload> {

    public JarvisValidation() {

        super(new NotNullAttributeRule<>(JarvisPayload::getGiornoGas, "giornoGas"),
                new NotEmptyListRule<>(JarvisPayload::getListaCicli, "listaCicli"));
    }
}
