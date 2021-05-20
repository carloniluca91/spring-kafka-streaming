package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.model.common.ValidationDto;

import static it.luca.spring.data.utils.Utils.isNotPresent;

public class WebdispSpecification extends SourceSpecification<WebdispPayload> {

    public WebdispSpecification(String topicName) {
        super(DataSourceId.WEBDISP, DataSourceType.XML, WebdispPayload.class, topicName);
    }

    //@Override
    public ValidationDto validate(WebdispPayload input) {

        boolean validation;
        String message;

        if (isNotPresent(input.getDataOraInvio())) {
            validation = false;
            message = "dataOraInvio is";
        }
        return null;
    }
}
