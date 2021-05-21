package it.luca.spring.data.model.validation;

public class NullAttributeDescription extends InvalidAttributeDescription {

    public NullAttributeDescription(String attributeName) {
        super(ValidationType.NULL_ATTRIBUTE, attributeName);
    }
}
