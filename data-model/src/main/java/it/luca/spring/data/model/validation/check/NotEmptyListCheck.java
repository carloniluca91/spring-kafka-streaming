package it.luca.spring.data.model.validation.check;

import it.luca.spring.data.model.validation.common.ValidationType;

import java.util.List;
import java.util.function.Function;

import static it.luca.utils.functional.Optional.isPresent;


/**
 * Validation rule for a not-null and non-empty list of a POJO
 * @param <T> POJO type
 */

public class NotEmptyListCheck<T> extends AttributeCheck<T, List<?>> {

    public NotEmptyListCheck(Function<T, List<?>> function, String attributeName) {

        super(function, x -> isPresent(x) && !x.isEmpty(), attributeName, ValidationType.EMPTY_LIST);
    }
}
