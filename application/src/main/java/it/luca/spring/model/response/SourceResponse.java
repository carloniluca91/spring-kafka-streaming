package it.luca.spring.model.response;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.utils.DatePattern;
import lombok.Data;

import static it.luca.spring.data.utils.Utils.*;

@Data
public class SourceResponse {

    private final String date = now(DatePattern.DEFAULT_DATE);
    private final String timestamp = now(DatePattern.DEFAULT_TIMESTAMP);
    private final DataSourceId dataSourceId;
    private final ResponseCode responseCode;
    private final String responseMessage;

    public SourceResponse(DataSourceId dataSourceId) {

        this(dataSourceId, null);
    }

    public SourceResponse(DataSourceId dataSourceId, Exception exception) {

        this.dataSourceId = dataSourceId;
        this.responseCode = isPresent(exception) ? ResponseCode.OK : ResponseCode.KO;
        this.responseMessage = orElse(exception, Exception::getMessage, "Message has been received, processed and sent");
    }
}
