package it.luca.spring.data.model.validation.rules;

import it.luca.spring.data.model.validation.TestBean;
import it.luca.spring.data.model.validation.common.AttributeValidationDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotNullAttributeRuleTest extends RuleTest<String> {

    public NotNullAttributeRuleTest() {
        super(new NotNullAttributeRule<>(TestBean::getName, ATTRIBUTE_NAME));
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