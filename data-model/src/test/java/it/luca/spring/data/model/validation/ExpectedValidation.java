package it.luca.spring.data.model.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
public class ExpectedValidation {

    protected final String sampleFileName;

    @Accessors(fluent = true)
    protected final Boolean isValid;

    protected final Integer numberOfRejectedRules;
}
