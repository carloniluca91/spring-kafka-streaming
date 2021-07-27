package it.luca.spring.data.model.validation.check;

import it.luca.utils.functional.Optional;

import java.util.function.Function;

/**
 * Validation rule for not-null attributes of a POJO
 * @param <T> POJO type
 * @param <R> attribute type
 */

public class NotNullAttributeCheck<T, R> extends AttributeCheck<T, R> {

    public NotNullAttributeCheck(String attributeName, Function<T, R> function) {

        super(attributeName, function, Optional::isPresent);
    }

    @Override
    protected String getErrorDescription() {

        return String.format("'%s' attribute is null", attributeName);
    }
}
