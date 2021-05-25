package it.luca.spring.exception;

import it.luca.spring.data.model.validation.common.ObjectValidationDto;

public class InputValidationException extends Exception {

    public InputValidationException(ObjectValidationDto dto) {
        super(String.join(", ", dto.getMessages()));
    }
}
