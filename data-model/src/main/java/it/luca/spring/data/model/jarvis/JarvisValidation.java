package it.luca.spring.data.model.jarvis;

import it.luca.spring.data.model.validation.common.ObjectValidation;
import it.luca.spring.data.model.validation.rules.NotEmptyListRule;
import it.luca.spring.data.model.validation.rules.NotNullRule;

public class JarvisValidation extends ObjectValidation<JarvisPayload> {

    public JarvisValidation() {

        super(new NotNullRule<>(JarvisPayload::getGiornoGas, "giornoGas"),
                new NotEmptyListRule<>(JarvisPayload::getListaCicli, "listaCicli"));
    }
}
