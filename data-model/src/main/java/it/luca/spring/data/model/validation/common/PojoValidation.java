package it.luca.spring.data.model.validation.common;

import it.luca.spring.data.model.validation.rules.AttributeRule;

import java.util.Arrays;
import java.util.List;

import static it.luca.spring.data.utils.Utils.filter;
import static it.luca.spring.data.utils.Utils.map;

/**
 * Class representing a set of validation rule(s) for POJO T
 * @param <T> POJO type
 */

public abstract class PojoValidation<T> {

    private final List<AttributeRule<T, ?>> rules;

    @SafeVarargs
    public PojoValidation(AttributeRule<T, ?>... rules) {

        this.rules = Arrays.asList(rules);
    }

    /**
     * Validates instance of type T
     * @param input instance of T
     * @return validation bean
     */

    public PojoValidationDto validate(T input) {

        List<AttributeValidationDto> attributeValidations = map(rules, x -> x.validate(input));
        boolean validInput = attributeValidations.stream().allMatch(AttributeValidationDto::isValid);
        List<String> messages = validInput ? null :
                map(filter(attributeValidations, x -> !x.isValid()), AttributeValidationDto::getMessage);

        return new PojoValidationDto(validInput, messages);
    }
}