package it.luca.spring.data.model.validation.common;

import it.luca.spring.data.model.validation.rules.Validation;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class ObjectValidation<T> {

    private final List<Validation<T, ?>> validations;

    @SafeVarargs
    public ObjectValidation(Validation<T, ?>... validations) {

        this.validations = Arrays.asList(validations);
    }

    public ValidationDto validate(T input) {

        Predicate<Validation<T, ?>> predicate = x -> x.validate(input);
        boolean validInput = validations.stream().allMatch(predicate);
        String message = validInput ?
                "OK" :
                validations.stream()
                        .filter(predicate.negate())
                        .map(Validation::getDescription)
                        .collect(Collectors.joining(", "));

        return new ValidationDto(validInput, message);
    }
}
