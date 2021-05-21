package it.luca.spring.data.model.common;

import it.luca.spring.data.model.validation.ExpectedValidation;
import it.luca.spring.data.model.validation.common.ValidationDto;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import static it.luca.spring.data.utils.ObjectDeserializer.readValue;
import static org.junit.jupiter.api.Assertions.*;

@AllArgsConstructor
public abstract class SourceSpecificationTest<T> {

    protected final String sampleFolder;
    protected final SourceSpecification<T> specification;

    protected abstract List<ExpectedValidation> getExpectedValidations();

    @Test
    public void validateSamples() throws IOException {

        List<ExpectedValidation> validations = getExpectedValidations();
        for (ExpectedValidation expected: validations) {

            String filePath = Paths.get("samples", sampleFolder, expected.getSampleFileName()).toString();
            T instance = readValue(getClass().getClassLoader().getResourceAsStream(filePath), specification);
            ValidationDto actual = specification.getObjectValidation().validate(instance);
            assertEquals(expected.isValid(), actual.isValid());
            if (expected.isValid()) {
                assertNull(actual.getMessage());
            } else {
                assertEquals(expected.getNumberOfRejectedRules(), actual.getMessage().split(", ").length);
            }
        }
    }
}