package it.luca.spring.data.model.common;

import it.luca.spring.data.model.validation.check.AttributeCheck;
import it.luca.spring.data.model.validation.check.NotEmptyListCheck;
import it.luca.spring.data.model.validation.check.NotNullAttributeCheck;
import it.luca.spring.data.model.validation.dto.AttributeValidationDto;
import it.luca.spring.data.model.validation.dto.PojoValidationDto;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static it.luca.utils.functional.Stream.filter;
import static it.luca.utils.functional.Stream.map;

/**
 * Class representing a set of validation rule(s) for POJO T
 * @param <T> POJO type
 */

public class PojoValidation<T> {

    private final List<AttributeCheck<T, ?>> attributeChecks = new ArrayList<>();

    public static <T> PojoValidation2Builder<T> builder() {

        return new PojoValidation2Builder<>();
    }

    /**
     * Validates instance of type T
     * @param instance instance of T
     * @return validation bean
     */

    public PojoValidationDto validate(T instance) {

        List<AttributeValidationDto> attributeValidations = map(attributeChecks, x -> x.validate(instance));
        boolean validInput = attributeValidations.stream().allMatch(AttributeValidationDto::isValid);
        List<String> messages = validInput ? null :
                map(filter(attributeValidations, x -> !x.isValid()), AttributeValidationDto::getMessage);

        return new PojoValidationDto(validInput, messages);
    }

    /**
     * Builder class
     * @param <T> POJO type
     */

    public static class PojoValidation2Builder<T> {

        private final PojoValidation<T> pojoValidation = new PojoValidation<>();

        public <R> PojoValidation2Builder<T> withNotNullAttributeCheck(String attributeName, Function<T, R> function) {

            pojoValidation.attributeChecks.add(new NotNullAttributeCheck<>(attributeName, function));
            return this;
        }

        public PojoValidation2Builder<T> withNotNullOrEmptyListCheck(String attributeName, Function<T, List<?>> function) {

            pojoValidation.attributeChecks.add(new NotEmptyListCheck<>(attributeName, function));
            return this;
        }

        public PojoValidation<T> build() {

            return pojoValidation;
        }
    }
}
