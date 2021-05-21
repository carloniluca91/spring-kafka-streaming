package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.common.SourceSpecificationTest;
import it.luca.spring.data.model.validation.ExpectedValidation;

import java.util.Arrays;
import java.util.List;

class WebdispSpecificationTest extends SourceSpecificationTest<WebdispPayload> {

    public WebdispSpecificationTest() {
        super("webdisp", new WebdispSpecification("topic"));
    }

    @Override
    protected List<ExpectedValidation> getExpectedValidations() {

        return Arrays.asList(
                new ExpectedValidation("webdisp_empty.xml", false, 2),
                new ExpectedValidation("webdisp_empty_nomine.xml", false, 1),
                new ExpectedValidation("webdisp_no_dataOraInvio.xml", false, 1),
                new ExpectedValidation("webdisp_no_nomine.xml", false, 1),
                new ExpectedValidation("webdisp_valid.xml", true, 0));
    }
}