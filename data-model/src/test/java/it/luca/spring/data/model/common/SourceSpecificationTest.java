package it.luca.spring.data.model.common;

import it.luca.spring.data.model.validation.common.PojoValidationDto;
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

    protected void testSample(T sample, boolean expectedValidation, Integer expectedValidationFailures) {

        PojoValidationDto actual = specification.getPojoValidation().validate(sample);
        assertEquals(expectedValidation, actual.isValid());
        if (expectedValidation) {
            assertNull(actual.getMessages());
        } else {
            assertEquals(expectedValidationFailures, actual.getMessages().size());
        }
    }

    @Test
    public void testDeserialization() throws IOException {

        T instance = readValue(getClass().getClassLoader().getResourceAsStream(sampleFileName), specification);
        testSample(instance, true, 0);
    }

    public abstract void testSamples();
}