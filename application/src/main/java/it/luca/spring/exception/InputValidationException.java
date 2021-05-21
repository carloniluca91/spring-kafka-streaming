package it.luca.spring.exception;

import it.luca.spring.data.model.validation.common.ValidationDto;

public class InputValidationException extends Exception {

    public InputValidationException(ValidationDto validationDto) {
        super(validationDto.getMessage());
    }
}
