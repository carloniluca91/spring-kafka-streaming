package it.luca.spring.data.model.validation.rules;

import it.luca.spring.data.model.validation.TestBean;
import it.luca.spring.data.model.validation.common.ValidationDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotNullRuleTest extends RuleTest<String> {

    public NotNullRuleTest() {
        super(new NotNullAttributeRule<>(TestBean::getName, ATTRIBUTE_NAME));
    }

    @Test
    @Override
    public void validateFailure() {

        TestBean emptyBean = new TestBean();
        ValidationDto failedValidation = rule.validate(emptyBean);
        assertFalse(failedValidation.isValid());
        assertNotNull(failedValidation.getMessage());
    }

    @Test
    @Override
    public void validateSuccess() {

        TestBean nonEmptyBean = new TestBean();
        nonEmptyBean.setName("hello");
        ValidationDto successfulValidation = rule.validate(nonEmptyBean);
        assertTrue(successfulValidation.isValid());
        assertNull(successfulValidation.getMessage());
    }
}