package it.luca.spring.data.model.common;

import it.luca.spring.data.model.validation.dto.PojoValidationDto;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static it.luca.spring.data.utils.ObjectDeserializer.readValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@AllArgsConstructor
public abstract class SourceSpecificationTest<T> {

    protected final String sampleFileName;
    protected final SourceSpecification<T> specification;

    protected void testInstanceValidation(T instance, boolean expectedValidation, Integer expectedValidationFailures) {

        PojoValidationDto actual = specification.validate(instance);
        assertEquals(expectedValidation, actual.isPojoInstanceValid());
        if (expectedValidation) {
            assertNull(actual.getAttributeValidationMessages());
        } else {
            assertEquals(expectedValidationFailures, actual.getAttributeValidationMessages().size());
        }
    }

    @Test
    public void testSampleFile() throws IOException {

        T instance = readValue(getClass().getClassLoader().getResourceAsStream(sampleFileName), specification);
        testInstanceValidation(instance, true, 0);
        testSampleFileInstance(instance);

    }

    protected abstract void testSampleFileInstance(T instance);

    @Test
    public abstract void testValidation();
}