package it.luca.spring.data.model.validation.rules;

import it.luca.spring.data.model.validation.common.ValidationType;
import it.luca.utils.functional.FunctionalUtils;

import java.util.function.Function;

/**
 * Validation rule for not-null attributes of a POJO
 * @param <T> POJO type
 * @param <R> attribute type
 */

public class NotNullAttributeRule<T, R> extends AttributeRule<T, R> {

    public NotNullAttributeRule(Function<T, R> function, String attributeName) {

        super(function, FunctionalUtils::isPresent, attributeName, ValidationType.NULL_ATTRIBUTE);
    }
}
