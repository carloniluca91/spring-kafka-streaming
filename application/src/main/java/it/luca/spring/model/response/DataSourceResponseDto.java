package it.luca.spring.model.response;

import it.luca.spring.data.enumeration.IngestionOperationCode;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.utils.DatePattern;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static it.luca.utils.functional.FunctionalUtils.orElse;
import static it.luca.utils.time.TimeUtils.now;

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

    public DataSourceResponseDto(SourceSpecification<?> specification, HttpStatus httpStatus, Exception exception) {

        messageTs = now(DatePattern.DEFAULT_TIMESTAMP);
        messageDt = now(DatePattern.DEFAULT_DATE);
        this.dataSourceId = specification.getDataSourceId().name();
        this.dataSourceType = specification.getDataSourceType().name();
        ingestionOperationCode = orElse(exception, e -> IngestionOperationCode.KO, IngestionOperationCode.OK);
        ingestionOperationMessage = orElse(exception, Exception::getMessage, "Message received");
        this.httpStatus = httpStatus;
        this.httpStatusDescription = httpStatus.getReasonPhrase();
    }
}
