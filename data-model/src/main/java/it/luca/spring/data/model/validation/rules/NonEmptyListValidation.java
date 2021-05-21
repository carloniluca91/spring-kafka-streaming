package it.luca.spring.data.model.validation.rules;

import it.luca.spring.data.model.validation.common.ValidationType;

import java.util.List;
import java.util.function.Function;

public class NonEmptyListValidation<T> extends Validation<T, List<?>> {

    public NonEmptyListValidation(Function<T, List<?>> function, String attributeName) {
        super(function, x -> !x.isEmpty(), attributeName, ValidationType.EMPTY_LIST);
    }
}
