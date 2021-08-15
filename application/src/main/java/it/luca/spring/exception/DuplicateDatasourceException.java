package it.luca.spring.exception;

import it.luca.spring.data.model.common.DataSourceSpecification;

public class DuplicateDatasourceException extends RuntimeException {

    public DuplicateDatasourceException(int duplicates, Class<?> dataClass) {

        super(String.format("Found %s instances of %s for dataClass %s",
                duplicates,
                DataSourceSpecification.class.getName(),
                dataClass.getName()));
    }
}
