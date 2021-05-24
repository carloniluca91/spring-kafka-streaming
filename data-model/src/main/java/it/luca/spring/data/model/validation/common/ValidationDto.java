package it.luca.spring.data.model.validation.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Validation bean
 */

@Getter
@AllArgsConstructor
public class ValidationDto {

    @Accessors(fluent = true)
    private final Boolean isValid;

    private final String message;
}
