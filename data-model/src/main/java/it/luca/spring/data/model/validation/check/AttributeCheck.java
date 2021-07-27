package it.luca.spring.data.model.validation.check;

import it.luca.spring.data.model.validation.dto.AttributeValidationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Class defining a single validation rule for an attribute of a POJO
 * @param <T> POJO type
 * @param <R> attribute type
 */

@Getter
@AllArgsConstructor
public abstract class AttributeCheck<T, R> {

    protected final String attributeName;
    protected final Function<T, R> function;
    protected final Predicate<R> predicate;

    /**
     * Validates instance of T by applying given function and testing given predicate
     * @param inputObject instance of T
     * @return validation bean
     */

    public AttributeValidationDto validate(T inputObject) {

        boolean valid = predicate.test(function.apply(inputObject));
        return new AttributeValidationDto(valid, valid ? null : getErrorDescription());
    }

    protected abstract String getErrorDescription();
}
