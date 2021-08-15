package it.luca.spring.data.model.common;

import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.validation.dto.PojoValidationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DataSourceSpecification<T> {

    @Getter
    private String id;

    @Getter
    private DataSourceType type;

    @Getter
    private Class<T> dataClass;

    private Class<? extends SampleValidator<T>> validatorClass;

    @Getter
    private String topic;

    public void setType(String type) {
        this.type = DataSourceType.valueOf(type.toUpperCase());
    }

    @SuppressWarnings("unchecked")
    public void setDataClass(String dataClass) throws ClassNotFoundException {
        this.dataClass = (Class<T>) Class.forName(dataClass);
    }

    @SuppressWarnings("unchecked")
    public void setValidatorClass(String validatorClass) throws ClassNotFoundException {
        this.validatorClass = (Class<? extends SampleValidator<T>>) Class.forName(validatorClass);
    }

    /**
     * Validate instance of type T
     * @param input instance of T
     * @return validation bean
     */

    public PojoValidationDto validate(T input) throws InstantiationException, IllegalAccessException {

        return validatorClass
                .newInstance()
                .validate(input);
    }
}
