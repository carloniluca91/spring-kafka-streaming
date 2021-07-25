package it.luca.spring.data.model.validation.check;

import it.luca.spring.data.model.validation.TestBean;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class RuleTest<R> {

    protected final static String ATTRIBUTE_NAME = "attribute";
    protected final AttributeCheck<TestBean, R> rule;

    public abstract void validateFailure();

    public abstract void validateSuccess();
}