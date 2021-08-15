package it.luca.spring.data.model.common;

import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.validation.dto.PojoValidationDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static it.luca.spring.data.utils.ObjectDeserializer.deserialize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public abstract class DataSourceSpecificationTest<T> {

    protected final String sampleFileName;
    protected final Class<T> dataClass;
    protected final Class<? extends SampleValidator<T>> validatorClass;
    protected final DataSourceSpecification<T> specification;

    public DataSourceSpecificationTest(String sampleFileName,
                                       Class<T> dataClass,
                                       Class<? extends SampleValidator<T>> validatorClass) throws ClassNotFoundException {

        this.sampleFileName = sampleFileName;
        this.dataClass = dataClass;
        this.validatorClass = validatorClass;
        this.specification = new DataSourceSpecification<>();
        specification.setType((sampleFileName.endsWith("xml") ? DataSourceType.XML : DataSourceType.JSON).name().toLowerCase());
        specification.setDataClass(dataClass.getName());
        specification.setValidatorClass(validatorClass.getName());
    }

    protected void testInstanceValidation(T instance, boolean expectedValidation, Integer expectedValidationFailures)
            throws InstantiationException, IllegalAccessException {

        PojoValidationDto actual = specification.validate(instance);
        assertEquals(expectedValidation, actual.isValid());
        if (expectedValidation) {
            assertNull(actual.getAttributeValidationMessages());
        } else {
            assertEquals(expectedValidationFailures, actual.getAttributeValidationMessages().size());
        }
    }

    @Test
    public void testSampleFile() throws IOException, InstantiationException, IllegalAccessException {

        T instance = deserialize(getClass().getClassLoader().getResourceAsStream(sampleFileName), specification);
        testInstanceValidation(instance, true, 0);
        testSampleFileInstance(instance);
    }

    protected abstract void testSampleFileInstance(T instance);

    @Test
    public abstract void testValidation() throws InstantiationException, IllegalAccessException;
}