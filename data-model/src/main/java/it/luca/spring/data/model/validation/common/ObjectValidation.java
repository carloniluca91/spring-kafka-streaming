package it.luca.spring.data.model.validation.common;

import it.luca.spring.data.model.validation.rules.AttributeRule;

import java.util.Arrays;
import java.util.List;

import static it.luca.spring.data.utils.Utils.filter;
import static it.luca.spring.data.utils.Utils.map;

/**
 * Class holding validation rule(s) for POJO of type T
 * @param <T> POJO type
 */

public abstract class ObjectValidation<T> {

    private final List<AttributeRule<T, ?>> rules;

    @SafeVarargs
    public ObjectValidation(AttributeRule<T, ?>... rules) {

        this.rules = Arrays.asList(rules);
    }

    /**
     * Validates instance of type T
     * @param input instance of T
     * @return validation bean
     */

    public ValidationDto validate(T input) {

        List<ValidationDto> attributeValidations = map(rules, x -> x.validate(input));
        boolean validInput = attributeValidations.stream().allMatch(ValidationDto::isValid);
        String message = validInput ?
                null :
                String.join(", ", map(filter(attributeValidations, x -> !x.isValid()), ValidationDto::getMessage));

        return new ValidationDto(validInput, message);
    }
}
