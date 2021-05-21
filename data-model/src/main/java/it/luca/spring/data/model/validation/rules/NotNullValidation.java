package it.luca.spring.data.model.validation.rules;

import it.luca.spring.data.model.validation.common.ValidationType;
import it.luca.spring.data.utils.Utils;

import java.util.function.Function;

public class NotNullValidation<T, R> extends Validation<T, R> {

    public NotNullValidation(Function<T, R> function, String attributeName) {

        super(function, Utils::isPresent, attributeName, ValidationType.NULL_ATTRIBUTE);
    }
}
