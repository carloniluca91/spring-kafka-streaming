package it.luca.spring.data.model.common;

import it.luca.spring.data.model.validation.dto.PojoValidationDto;

public interface SampleValidator<T> {

    PojoValidation<T> create();

    /**
     * Validates instance of type T
     * @param instance instance of T
     * @return validation bean
     */

    default PojoValidationDto validate(T instance) {

        return create().validate(instance);
    }
}
