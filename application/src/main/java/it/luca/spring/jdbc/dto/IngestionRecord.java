package it.luca.spring.jdbc.dto;

import it.luca.spring.data.enumeration.IngestionOperationCode;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.utils.DatePattern;
import it.luca.spring.model.dto.SentMessageDto;
import lombok.Data;

import java.sql.Timestamp;

import static it.luca.spring.data.utils.Utils.*;

@Data
public abstract class IngestionRecord {

    protected final Timestamp messageTs = Timestamp.valueOf(now());
    protected final String messageDt = now(DatePattern.DEFAULT_DATE);
    protected final String dataSourceId;
    protected final String dataSourceType;
    protected final String inputDataClass;
    protected final String topicName;
    protected final Integer topicPartition;
    protected final String ingestionOperationCode;
    protected final Long messageOffset;
    protected final String exceptionClass;
    protected final String exceptionMessage;
    protected final Timestamp insertTs = Timestamp.valueOf(now());
    protected final String insertDt = now(DatePattern.DEFAULT_DATE);

    public IngestionRecord(SourceSpecification<?> specification,
                           SentMessageDto sentMessageDto,
                           Exception exception) {

        dataSourceId = specification.getDataSourceId().name();
        dataSourceType = specification.getDataSourceType().name();
        inputDataClass = specification.getInputDataClass().getName();
        topicName = specification.getTopicName();
        topicPartition = orElse(sentMessageDto, SentMessageDto::getTopicPartition, -1);
        ingestionOperationCode = (isPresent(exception) ? IngestionOperationCode.KO : IngestionOperationCode.OK).name();
        messageOffset = orElse(sentMessageDto, SentMessageDto::getMessageOffset, -1L);
        exceptionClass = orNull(exception, x -> x.getClass().getName());
        exceptionMessage = orNull(exception, Throwable::getMessage);
    }
}
