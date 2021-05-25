package it.luca.spring.data.model.common;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.validation.common.PojoValidation;
import it.luca.spring.data.model.validation.common.PojoValidationDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Bean storing dataSource specification
 * @param <T> data type
 */

@Getter
@AllArgsConstructor
public abstract class SourceSpecification<T> {

    protected final DataSourceId dataSourceId;
    protected final DataSourceType dataSourceType;
    protected final Class<T> inputDataClass;
    protected final PojoValidation<T> pojoValidation;
    protected final String topicName;

    /**
     * Validate instance of type T
     * @param input instance of T
     * @return validation bean
     */

    public PojoValidationDto validate(T input) {

        return pojoValidation.validate(input);
    }
}
