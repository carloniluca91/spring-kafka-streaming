package it.luca.cedacri.model.response;

import it.luca.cedacri.enumeration.DataSourceId;
import it.luca.cedacri.enumeration.DatePattern;
import it.luca.cedacri.utils.Utils;
import lombok.Data;

import java.util.Optional;

@Data
public class SourceResponse {

    private final String date = Utils.now(DatePattern.DEFAULT_DATE);
    private final String timestamp = Utils.now(DatePattern.DEFAULT_TIMESTAMP);
    private final DataSourceId dataSourceId;
    private final String responseCode;
    private final String responseMessage;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public SourceResponse(DataSourceId dataSourceId, Optional<Exception> optionalException) {

        this.dataSourceId = dataSourceId;
        this.responseCode = optionalException.isEmpty() ? "OK" : "KO";
        this.responseMessage = optionalException
                .map(Throwable::getMessage)
                .orElse("Message has been received, processed and sent");
    }
}
