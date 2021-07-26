package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.SourceSpecification;

public class WebdispSpecification extends SourceSpecification<WebdispPayload> {

    public WebdispSpecification() {

        super("WEBDISP", WebdispPayload.class, DataSourceType.XML, new WebdispValidation());
    }
}
