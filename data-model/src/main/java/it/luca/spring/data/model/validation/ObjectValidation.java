package it.luca.spring.data.model.validation;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public abstract class ObjectValidation<T> {

    private final List<ValidationRule<T>> validationRules;

    @SafeVarargs
    public ObjectValidation(ValidationRule<T>... validationRules) {

        this.validationRules = Arrays.asList(validationRules);
    }

    public ValidationDto validate(T input) {

        Predicate<ValidationRule<T>> predicate = x -> x.getPredicate().test(input);
        boolean validInput = validationRules.stream().allMatch(predicate);
        String message = validInput ?
                "OK" :
                validationRules.stream()
                        .filter(predicate.negate())
                        .map(x -> x.getRationale().getDescription())
                        .collect(Collectors.joining(", "));

        return new ValidationDto(validInput, message);
    }
}
