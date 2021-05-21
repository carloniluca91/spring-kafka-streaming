package it.luca.spring.data.model.webdisp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import it.luca.spring.data.utils.XMLField;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class WebdispPayload {

    private final String dataOraInvio;
    private final List<WebdispNomina> nomine;

    @JsonCreator
    public WebdispPayload(@JacksonXmlProperty(localName = XMLField.DATA_ORA_INVIO) String dataOraInvio,
                          @JacksonXmlProperty(localName = XMLField.NOMINE) List<WebdispNomina> nomine) {

        this.dataOraInvio = dataOraInvio;
        this.nomine = nomine;
    }
}
