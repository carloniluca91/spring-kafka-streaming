package it.luca.spring.data.model.common;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.validation.ObjectValidation;
import it.luca.spring.data.model.validation.ValidationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class SourceSpecification<T> {

    protected final DataSourceId dataSourceId;
    protected final DataSourceType dataSourceType;
    protected final Class<T> inputDataClass;
    protected final ObjectValidation<T> objectValidation;
    protected final String topicName;

    public ValidationDto validate(T input) {

        return objectValidation.validate(input);
    }
}
