package it.luca.spring.exception;

import it.luca.spring.data.model.validation.dto.PojoValidationDto;

public class InputValidationException extends Exception {

    public InputValidationException(PojoValidationDto dto) {
        super(String.join(", ", dto.getAttributeValidationMessages()));
    }
}
