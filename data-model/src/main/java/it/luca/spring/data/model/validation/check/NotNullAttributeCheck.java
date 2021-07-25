package it.luca.spring.data.model.validation.check;

import it.luca.spring.data.model.validation.common.ValidationType;
import it.luca.utils.functional.Optional;

import java.util.function.Function;

/**
 * Validation rule for not-null attributes of a POJO
 * @param <T> POJO type
 * @param <R> attribute type
 */

public class NotNullAttributeCheck<T, R> extends AttributeCheck<T, R> {

    public NotNullAttributeCheck(Function<T, R> function, String attributeName) {

        super(function, Optional::isPresent, attributeName, ValidationType.NULL_ATTRIBUTE);
    }
}
