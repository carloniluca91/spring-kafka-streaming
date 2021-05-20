package it.luca.spring.model.response;

import it.luca.spring.data.enumeration.IngestionOperationCode;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.utils.DatePattern;
import lombok.Getter;

import static it.luca.spring.data.utils.Utils.now;
import static it.luca.spring.data.utils.Utils.orElse;

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

    public DataSourceResponseDto(SourceSpecification<?> specification, Exception exception) {

        messageTs = now(DatePattern.DEFAULT_TIMESTAMP);
        messageDt = now(DatePattern.DEFAULT_DATE);
        this.dataSourceId = specification.getDataSourceId().name();
        this.dataSourceType = specification.getDataSourceType().name();
        ingestionOperationCode = orElse(exception, e -> IngestionOperationCode.KO, IngestionOperationCode.OK);
        ingestionOperationMessage = orElse(exception, Exception::getMessage, "Message received");
    }
}
