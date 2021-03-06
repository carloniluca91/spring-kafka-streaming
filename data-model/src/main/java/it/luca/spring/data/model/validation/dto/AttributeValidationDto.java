package it.luca.spring.data.model.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * Represents the output of validating a POJO attribute.
 * If .isValid() is true, message is null. Otherwise, message represents failed validation rationale
 */

@Getter
@AllArgsConstructor
public class AttributeValidationDto {

    @Accessors(fluent = true)
    private final Boolean isValid;

    private final String message;
}
