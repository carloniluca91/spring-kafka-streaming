package it.luca.spring.data.model.validation.common;

import it.luca.spring.data.model.validation.check.AttributeCheck;

import java.util.Arrays;
import java.util.List;

import static it.luca.utils.functional.Stream.map;
import static it.luca.utils.functional.Stream.filter;

/**
 * Class representing a set of validation rule(s) for POJO T
 * @param <T> POJO type
 */

public abstract class PojoValidation<T> {

    private final List<AttributeCheck<T, ?>> attributeChecks;

    @SafeVarargs
    public PojoValidation(AttributeCheck<T, ?>... attributeChecks) {

        this.attributeChecks = Arrays.asList(attributeChecks);
    }

    /**
     * Validates instance of type T
     * @param input instance of T
     * @return validation bean
     */

    public PojoValidationDto validate(T input) {

        List<AttributeValidationDto> attributeValidations = map(attributeChecks, x -> x.validate(input));
        boolean validInput = attributeValidations.stream().allMatch(AttributeValidationDto::isValid);
        List<String> messages = validInput ? null :
                map(filter(attributeValidations, x -> !x.isValid()), AttributeValidationDto::getMessage);

        return new PojoValidationDto(validInput, messages);
    }
}
