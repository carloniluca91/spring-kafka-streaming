package it.luca.spring.model;

import it.luca.spring.data.enumeration.IngestionOperationCode;
import it.luca.spring.data.model.common.DataSourceSpecification;
import it.luca.spring.data.utils.DatePattern;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static it.luca.utils.functional.Optional.isPresent;
import static it.luca.utils.functional.Optional.orElse;
import static it.luca.utils.time.Supplier.now;

/**
 * Dto representing response to send back to dataSources
 */

@Getter
public class DataSourceResponseDto {

    private final String messageTs;
    private final String messageDt;
    private final String dataSourceId;
    private final String dataSourceType;
    private final IngestionOperationCode ingestionOperationCode;
    private final String ingestionOperationMessage;
    private final HttpStatus httpStatus;
    private final String httpStatusDescription;

    public DataSourceResponseDto(DataSourceSpecification<?> specification, HttpStatus httpStatus, Exception exception) {

        messageTs = now(DatePattern.DEFAULT_TIMESTAMP);
        messageDt = now(DatePattern.DEFAULT_DATE);
        this.dataSourceId = specification.getId();
        this.dataSourceType = specification.getType().name();
        ingestionOperationCode = isPresent(exception) ? IngestionOperationCode.KO : IngestionOperationCode.OK;
        ingestionOperationMessage = orElse(exception, Exception::getMessage, "Message received");
        this.httpStatus = httpStatus;
        this.httpStatusDescription = httpStatus.getReasonPhrase();
    }
}
