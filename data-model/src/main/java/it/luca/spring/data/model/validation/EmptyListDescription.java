package it.luca.spring.data.model.validation;

public class EmptyListDescription extends InvalidAttributeDescription {

    public EmptyListDescription(String attributeName) {
        super(ValidationType.EMPTY_LIST, attributeName);
    }
}
