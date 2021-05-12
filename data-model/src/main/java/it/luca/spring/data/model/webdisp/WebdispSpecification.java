package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.SourceSpecification;

import java.util.List;

public class WebdispSpecification extends SourceSpecification<WebdispWrapper, WebdispAvro, WebdispAvroSerializer> {

    public WebdispSpecification() {
        super(DataSourceId.WEBDISP, DataSourceType.XML, WebdispWrapper.class, WebdispAvro.class, WebdispAvroSerializer.class);
    }

    @Override
    public List<WebdispAvro> getAvroRecords(WebdispWrapper input) {
        return null;
    }
}
