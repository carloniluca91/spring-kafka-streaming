package it.luca.spring.data.model.common;

import it.luca.spring.data.model.validation.common.ObjectValidationDto;
import lombok.AllArgsConstructor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@AllArgsConstructor
public abstract class SourceSpecificationTest<T> {

    protected final String sampleFolder;
    protected final SourceSpecification<T> specification;

    protected void testSample(T sample, boolean expectedValidation, Integer expectedValidationFailures) {

        ObjectValidationDto actual = specification.getObjectValidation().validate(sample);
        assertEquals(expectedValidation, actual.isValid());
        if (expectedValidation) {
            assertNull(actual.getMessages());
        } else {
            assertEquals(expectedValidationFailures, actual.getMessages().size());
        }
    }

    public abstract void testSamples();
}