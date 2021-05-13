package it.luca.spring.jdbc.dto;

import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.utils.DatePattern;
import lombok.Data;

import java.sql.Timestamp;

import static it.luca.spring.data.utils.Utils.now;

@Data
public abstract class BaseDto {

    protected final Timestamp messageTs = Timestamp.valueOf(now());
    protected final String messageDt = now(DatePattern.DEFAULT_DATE);
    protected final String dataSourceId;
    protected final String dataSourceType;
    protected final String inputDataClass;
    protected final String avroRecordClass;
    protected final String topicName;
    protected final Timestamp insertTs = Timestamp.valueOf(now());
    protected final String insertDt = now(DatePattern.DEFAULT_DATE);

    public BaseDto(SourceSpecification<?, ?> specification) {

        dataSourceId = specification.getDataSourceId().name();
        dataSourceType = specification.getDataSourceType().name();
        inputDataClass = specification.getInputDataClass().getName();
        avroRecordClass = specification.getAvroRecordClass().getName();
        topicName = specification.getTopicName();
    }
}
