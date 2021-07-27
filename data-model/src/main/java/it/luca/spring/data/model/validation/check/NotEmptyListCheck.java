package it.luca.spring.data.model.validation.check;

import java.util.List;
import java.util.function.Function;

import static it.luca.utils.functional.Optional.isPresent;

/**
 * Validation rule for a not-null and non-empty list of a POJO
 * @param <T> POJO type
 */

public class NotEmptyListCheck<T> extends AttributeCheck<T, List<?>> {

    public NotEmptyListCheck(String attributeName, Function<T, List<?>> function) {

        super(attributeName, function, x -> isPresent(x) && !x.isEmpty());
    }

    @Override
    protected String getErrorDescription() {

        return String.format("'%s' list is null or empty", attributeName);
    }
}
