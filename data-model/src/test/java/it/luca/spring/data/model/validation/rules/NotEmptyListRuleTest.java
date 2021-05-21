package it.luca.spring.data.model.validation.rules;

import it.luca.spring.data.model.validation.TestBean;
import it.luca.spring.data.model.validation.common.ValidationDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NotEmptyListRuleTest extends RuleTest<List<?>> {

    public NotEmptyListRuleTest() {

        super(new NotEmptyListRule<>(TestBean::getList, ATTRIBUTE_NAME));
    }

    @Test
    @Override
    public void validateFailure() {

        // Null list
        TestBean firstBean = new TestBean();

        // Empty list
        TestBean secondBean = new TestBean();
        secondBean.setList(new ArrayList<>());
        Stream.of(firstBean, secondBean).forEach(x -> {

            ValidationDto validationDto = rule.validate(x);
            assertFalse(validationDto.isValid());
            assertNotNull(validationDto.getMessage());
        });
    }

    @Test
    @Override
    public void validateSuccess() {

        TestBean emptyBean = new TestBean();
        emptyBean.setList(Arrays.asList("hello", "world"));
        ValidationDto failedValidation = rule.validate(emptyBean);
        assertTrue(failedValidation.isValid());
        assertNull(failedValidation.getMessage());
    }
}