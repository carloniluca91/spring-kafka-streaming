package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.common.SourceSpecificationTest;
import it.luca.spring.data.model.validation.common.ValidationDto;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class WebdispSpecificationTest extends SourceSpecificationTest<WebdispPayload> {

    public WebdispSpecificationTest() throws IOException {
        super("webdisp.xml", new WebdispSpecification("topic"));
    }

    @Test
    @Override
    public void assertReadValue() {

        assertNotNull(instance);
        assertNotNull(instance.getDataOraInvio());
        assertNotNull(instance.getNomine());
        assertFalse(instance.getNomine().isEmpty());
    }

    @Test
    @Override
    public void assertObjectValidation() {

        ValidationDto validationDto = specification.validate(instance);
        assertTrue(validationDto.isValid());
        assertEquals("OK", validationDto.getMessage());
    }
}