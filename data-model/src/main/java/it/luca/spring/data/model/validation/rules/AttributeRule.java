package it.luca.spring.data.model.validation.rules;

import it.luca.spring.data.model.validation.common.AttributeValidationDto;
import it.luca.spring.data.model.validation.common.ValidationType;
import lombok.AllArgsConstructor;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Class defining a single validation rule for an attribute of a POJO
 * @param <T> POJO type
 * @param <R> attribute type
 */

@AllArgsConstructor
public abstract class AttributeRule<T, R> {

    private final Function<T, R> function;
    private final Predicate<R> predicate;
    private final String attributeName;
    private final ValidationType validationType;

    /**
     * Validates instance of T by applying given function and testing given predicate
     * @param inputObject instance of T
     * @return validation bean
     */

    public AttributeValidationDto validate(T inputObject) {

        boolean valid = predicate.test(function.apply(inputObject));
        String errorDescription;
        switch (validationType) {
            case EMPTY_LIST: errorDescription = String.format("'%s' list is null or empty", attributeName); break;
            case NULL_ATTRIBUTE: errorDescription = String.format("'%s' attribute is null", attributeName); break;
            default: throw new IllegalArgumentException(String.format("Unrecognized validationType: %s", validationType));
        }

        return new AttributeValidationDto(valid, valid ? null : errorDescription);
    }
}
