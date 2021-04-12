package it.luca.spring.service;

import it.luca.spring.enumeration.DataSourceId;
import it.luca.spring.enumeration.DatePattern;
import it.luca.spring.enumeration.ResponseCode;
import it.luca.spring.utils.Utils;
import lombok.Data;

import java.util.Optional;

@Data
public class SourceResponse {

    private final String date = Utils.now(DatePattern.DEFAULT_DATE);
    private final String timestamp = Utils.now(DatePattern.DEFAULT_TIMESTAMP);
    private final DataSourceId dataSourceId;
    private final ResponseCode responseCode;
    private final String responseMessage;

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public SourceResponse(DataSourceId dataSourceId, Optional<Exception> optionalException) {

        this.dataSourceId = dataSourceId;
        this.responseCode = optionalException.isEmpty() ? ResponseCode.OK : ResponseCode.KO;
        this.responseMessage = optionalException
                .map(Exception::getMessage)
                .orElse("Message has been received, processed and sent");
    }
}
