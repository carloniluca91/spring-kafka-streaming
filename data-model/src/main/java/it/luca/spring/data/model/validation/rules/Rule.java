package it.luca.spring.data.model.validation.rules;

import it.luca.spring.data.model.validation.common.ValidationDto;
import it.luca.spring.data.model.validation.common.ValidationType;
import lombok.AllArgsConstructor;

import java.util.function.Function;
import java.util.function.Predicate;

@AllArgsConstructor
public abstract class Rule<T, R> {

    private final Function<T, R> function;
    private final Predicate<R> predicate;
    private final String attributeName;
    private final ValidationType validationType;

    public ValidationDto validate(T inputObject) {

        boolean valid = predicate.test(function.apply(inputObject));
        String erroDescription;
        //noinspection SwitchStatementWithTooFewBranches
        switch (validationType) {
            case EMPTY_LIST: erroDescription = String.format("%s list is empty", attributeName); break;
            default: erroDescription = String.format("%s attribute is null", attributeName); break;
        }

        return new ValidationDto(valid, valid ? null : erroDescription);
    }
}
