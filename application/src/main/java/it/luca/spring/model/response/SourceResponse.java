package it.luca.spring.model.response;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.utils.DatePattern;
import lombok.Data;

import java.util.Optional;

import static it.luca.spring.data.utils.Utils.now;

@Data
public class SourceResponse {

    private final String date = now(DatePattern.DEFAULT_DATE);
    private final String timestamp = now(DatePattern.DEFAULT_TIMESTAMP);
    private final DataSourceId dataSourceId;
    private final ResponseCode responseCode;
    private final String responseMessage;

    public SourceResponse(DataSourceId dataSourceId, Optional<Exception> optionalException) {

        this.dataSourceId = dataSourceId;
        this.responseCode = optionalException.isEmpty() ? ResponseCode.OK : ResponseCode.KO;
        this.responseMessage = optionalException.isEmpty() ? "Message has been received, processed and sent":
                optionalException.get().getMessage();
    }
}
