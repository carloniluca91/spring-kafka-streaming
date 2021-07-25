package it.luca.spring.data.model.common;

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

    private final Class<T> inputDataClass;
    private final DataSourceType dataSourceType;
    private final PojoValidation<T> pojoValidation;

    /**
     * Validate instance of type T
     * @param input instance of T
     * @return validation bean
     */

    public PojoValidationDto validate(T input) {

        return getPojoValidation().validate(input);
    }

    public String getDataSourceId() {

        return getInputDataClass().getSimpleName().toUpperCase();
    }
}
