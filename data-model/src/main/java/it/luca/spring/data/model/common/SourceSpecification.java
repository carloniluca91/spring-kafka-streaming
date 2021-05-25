package it.luca.spring.data.model.common;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.validation.common.ObjectValidation;
import it.luca.spring.data.model.validation.common.ObjectValidationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Bean storing sataSource specification
 * @param <T> data type
 */

@Getter
@AllArgsConstructor
public abstract class SourceSpecification<T> {

    protected final DataSourceId dataSourceId;
    protected final DataSourceType dataSourceType;
    protected final Class<T> inputDataClass;
    protected final ObjectValidation<T> objectValidation;
    protected final String topicName;

    /**
     * Validate instance of type T
     * @param input instance of T
     * @return validation bean
     */

    public ObjectValidationDto validate(T input) {

        return objectValidation.validate(input);
    }
}
