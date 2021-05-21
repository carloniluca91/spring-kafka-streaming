package it.luca.spring.data.model.validation.rules;

import it.luca.spring.data.model.validation.common.ValidationType;
import lombok.AllArgsConstructor;

import java.util.function.Function;
import java.util.function.Predicate;

@AllArgsConstructor
public abstract class Validation<T, R> {

    private final Function<T, R> function;
    private final Predicate<R> predicate;
    private final String attributeName;
    private final ValidationType validationType;

    public boolean validate(T inputObject) {

        return predicate.test(function.apply(inputObject));
    }

    public String getDescription() {

        String description;
        //noinspection SwitchStatementWithTooFewBranches
        switch (validationType) {
            case EMPTY_LIST: description = String.format("%s list is empty", attributeName); break;
            default: description = String.format("%s attribute is null", attributeName); break;
        }

        return description;
    }
}
