package it.luca.spring.data.model.validation.rules;

import it.luca.spring.data.model.validation.TestBean;
import it.luca.spring.data.model.validation.common.ValidationDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotNullRuleTest {

    private final String ATTRIBUTE_NAME = "name";
    private final NotNullRule<TestBean, String> rule = new NotNullRule<>(TestBean::getName, ATTRIBUTE_NAME);

    @Test
    public void validate() {

        TestBean emptyBean = new TestBean();
        ValidationDto failedValidation = rule.validate(emptyBean);
        assertFalse(failedValidation.isValid());
        assertEquals(String.format("%s attribute is null", ATTRIBUTE_NAME), failedValidation.getMessage());

        TestBean nonEmptyBean = new TestBean();
        nonEmptyBean.setName("hello");
        ValidationDto successfulValidation = rule.validate(nonEmptyBean);
        assertTrue(successfulValidation.isValid());
        assertNull(successfulValidation.getMessage());
    }
}