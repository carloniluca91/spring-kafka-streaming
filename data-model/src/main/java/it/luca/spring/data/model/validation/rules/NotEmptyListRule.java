package it.luca.spring.data.model.validation.rules;

import it.luca.spring.data.model.validation.common.ValidationType;

import java.util.List;
import java.util.function.Function;

import static it.luca.spring.data.utils.Utils.isPresent;

/**
 * Validation rule for a not-null and non-empty list of a POJO
 * @param <T> POJO type
 */

public class NotEmptyListRule<T> extends Rule<T, List<?>> {

    public NotEmptyListRule(Function<T, List<?>> function, String attributeName) {

        super(function, x -> isPresent(x) && !x.isEmpty(), attributeName, ValidationType.EMPTY_LIST);
    }
}
