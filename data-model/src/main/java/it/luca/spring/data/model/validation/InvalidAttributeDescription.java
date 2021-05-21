package it.luca.spring.data.model.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class InvalidAttributeDescription {

    private final ValidationType validationType;
    private final String attributeName;

    public String getDescription() {

        String description;
        //noinspection SwitchStatementWithTooFewBranches
        switch (validationType) {
            case EMPTY_LIST: description = String.format("%s list is empty", attributeName); break;
            default: description = String.format("%s attribute is null", attributeName); break;
        }

        return description;
    }
}
