package it.luca.spring.data.model.common;

import static it.luca.utils.functional.Optional.isPresent;

public abstract class SourceSpecificationFactory<T> {

    private SourceSpecification<T> instance;

    protected SourceSpecificationFactory() {}

    public SourceSpecification<T> getInstance() {

        if (!isPresent(instance)) {
            instance = createInstance();
        }

        return instance;
    }

    protected abstract SourceSpecification<T> createInstance();
}
