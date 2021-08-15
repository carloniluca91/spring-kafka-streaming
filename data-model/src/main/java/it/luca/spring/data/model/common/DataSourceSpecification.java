package it.luca.spring.data.model.common;

import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.validation.dto.PojoValidationDto;
import lombok.Data;

@Data
public class DataSourceSpecification<T> {

    private String id;
    private DataSourceType type;
    private Class<T> dataClass;
    private Class<? extends SampleValidator<T>> validatorClass;
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
