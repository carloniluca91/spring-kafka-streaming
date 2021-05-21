package it.luca.spring.data.model.validation.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ValidationDto {

    private final Boolean isValid;

    @Getter()
    private final String message;

    public boolean isValid() {
        return isValid;
    }
}
