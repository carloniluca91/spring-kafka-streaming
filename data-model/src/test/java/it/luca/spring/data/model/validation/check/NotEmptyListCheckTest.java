package it.luca.spring.data.model.validation.check;

import it.luca.spring.data.model.validation.TestBean;
import it.luca.spring.data.model.validation.dto.AttributeValidationDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class NotEmptyListCheckTest extends AttributeCheckTest<List<?>> {

    public NotEmptyListCheckTest() {

        super(new NotEmptyListCheck<>(ATTRIBUTE_NAME, TestBean::getList));
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

            AttributeValidationDto attributeValidationDto = attributeCheck.validate(x);
            assertFalse(attributeValidationDto.isValid());
            assertNotNull(attributeValidationDto.getMessage());
        });
    }

    @Test
    @Override
    public void validateSuccess() {

        TestBean emptyBean = new TestBean();
        emptyBean.setList(Arrays.asList("hello", "world"));
        AttributeValidationDto failedValidation = attributeCheck.validate(emptyBean);
        assertTrue(failedValidation.isValid());
        assertNull(failedValidation.getMessage());
    }
}