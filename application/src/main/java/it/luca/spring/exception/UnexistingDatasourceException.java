package it.luca.spring.exception;

import it.luca.spring.data.model.common.SourceSpecification2;

public class UnexistingDatasourceException extends RuntimeException {

    public UnexistingDatasourceException(Class<?> dataClass) {

        super(String.format("Unable to find an instance of %s for dataClass %s",
                SourceSpecification2.class.getName(),
                dataClass.getName()));
    }
}
