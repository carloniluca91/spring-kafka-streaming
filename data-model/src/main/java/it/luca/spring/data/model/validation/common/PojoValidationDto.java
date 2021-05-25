package it.luca.spring.data.model.validation.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Represents output of POJO validation.
 * If .isValid() is true, messages are null. Otherwise, they represents failed validation rationale(s)
 */

@Getter
@AllArgsConstructor
public class PojoValidationDto {

    @Accessors(fluent = true)
    private final boolean isValid;

    private final List<String> messages;
}
