package it.luca.spring.data.model.jarvis;

import it.luca.spring.data.model.validation.common.PojoValidation;
import it.luca.spring.data.model.validation.check.NotEmptyListCheck;
import it.luca.spring.data.model.validation.check.NotNullAttributeCheck;

public class JarvisValidation extends PojoValidation<JarvisPayload> {

    public JarvisValidation() {

        super(new NotNullAttributeCheck<>(JarvisPayload::getGiornoGas, "giornoGas"),
                new NotEmptyListCheck<>(JarvisPayload::getListaCicli, "listaCicli"));
    }
}
