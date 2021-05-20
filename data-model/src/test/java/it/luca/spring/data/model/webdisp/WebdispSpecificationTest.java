package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.common.SourceSpecificationTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}