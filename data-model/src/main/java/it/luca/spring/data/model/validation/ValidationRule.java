package it.luca.spring.data.model.validation;

import lombok.Data;

import java.util.function.Predicate;

@Data
public class ValidationRule<T> {

    private final Predicate<T> predicate;
    private final InvalidAttributeDescription rationale;
}
