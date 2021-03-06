package it.luca.spring.data.model.validation.check;

import it.luca.spring.data.model.validation.TestBean;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class AttributeCheckTest<R> {

    protected final static String ATTRIBUTE_NAME = "attribute";
    protected final AttributeCheck<TestBean, R> attributeCheck;

    public abstract void validateFailure();

    public abstract void validateSuccess();
}
