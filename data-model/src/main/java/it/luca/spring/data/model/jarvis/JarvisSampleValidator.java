package it.luca.spring.data.model.jarvis;

import it.luca.spring.data.model.common.PojoValidation;
import it.luca.spring.data.model.common.SampleValidator;

public class JarvisSampleValidator implements SampleValidator<JarvisPayload> {

    @Override
    public PojoValidation<JarvisPayload> create() {

        return PojoValidation.<JarvisPayload>builder()
                .withNotNullAttributeCheck("giornoGas", JarvisPayload::getGiornoGas)
                .withNotNullOrEmptyListCheck("listaCicli", JarvisPayload::getListaCicli)
                .build();
    }
}
