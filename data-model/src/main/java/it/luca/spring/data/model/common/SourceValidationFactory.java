package it.luca.spring.data.model.common;

public interface SourceValidationFactory<T> {

    PojoValidation<T> create();
}
