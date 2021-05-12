package it.luca.spring.jdbc.bean;

import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.utils.DatePattern;
import lombok.Getter;

import java.sql.Timestamp;

import static it.luca.spring.data.utils.Utils.now;
import static it.luca.spring.data.utils.Utils.orNull;

@Getter
public class IngestionErrorRecord {

    private final String dataSourceId;
    private final String dataSourceType;
    private final String inputDataClass;
    private final String avroRecordClass;
    private final String topicName;
    private final Timestamp messageTs = Timestamp.valueOf(now());
    private final String messageDt = now(DatePattern.DEFAULT_DATE);
    private final String exceptionClass;
    private final String exceptionMessage;
    private final Timestamp insertTs = Timestamp.valueOf(now());
    private final String insertDt = now(DatePattern.DEFAULT_DATE);

    public IngestionErrorRecord(SourceSpecification<?, ?> specification, Exception exception) {

        dataSourceId = specification.getDataSourceId().name();
        dataSourceType = specification.getDataSourceType().name();
        inputDataClass = specification.getInputDataClass().getName();
        avroRecordClass = specification.getAvroRecordClass().getName();
        topicName = specification.getTopicName();
        exceptionClass = orNull(exception, e -> e.getClass().getName());
        exceptionMessage = orNull(exception, Exception::getMessage);
    }
}
