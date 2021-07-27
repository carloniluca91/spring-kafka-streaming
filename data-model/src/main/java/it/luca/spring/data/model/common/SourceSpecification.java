package it.luca.spring.data.model.common;

import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.validation.dto.PojoValidationDto;
import lombok.Builder;
import lombok.Getter;

/**
 * Bean storing dataSource specification
 * @param <T> data type
 */

@Getter
@Builder
public class SourceSpecification<T> {

    private final String dataSourceId;
    private final DataSourceType dataSourceType;
    private final Class<T> inputDataClass;
    private final PojoValidation<T> pojoValidation;

    /**
     * Validate instance of type T
     * @param input instance of T
     * @return validation bean
     */

    public PojoValidationDto validate(T input) {

        return pojoValidation.validate(input);
    }
}
