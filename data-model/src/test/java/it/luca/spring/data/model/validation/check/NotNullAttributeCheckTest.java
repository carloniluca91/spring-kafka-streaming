package it.luca.spring.data.model.validation.check;

import it.luca.spring.data.model.validation.TestBean;
import it.luca.spring.data.model.validation.dto.AttributeValidationDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotNullAttributeCheckTest extends AttributeCheckTest<String> {

    public NotNullAttributeCheckTest() {
        super(new NotNullAttributeCheck<>(ATTRIBUTE_NAME, TestBean::getName));
    }

    @Test
    @Override
    public void validateFailure() {

        TestBean emptyBean = new TestBean();
        AttributeValidationDto failedValidation = rule.validate(emptyBean);
        assertFalse(failedValidation.isValid());
        assertNotNull(failedValidation.getMessage());
    }

    @Test
    @Override
    public void validateSuccess() {

        TestBean nonEmptyBean = new TestBean();
        nonEmptyBean.setName("hello");
        AttributeValidationDto successfulValidation = rule.validate(nonEmptyBean);
        assertTrue(successfulValidation.isValid());
        assertNull(successfulValidation.getMessage());
    }
}