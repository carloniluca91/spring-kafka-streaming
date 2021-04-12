package it.luca.spring.jdbc.bean;

import it.luca.spring.enumeration.DataSourceId;
import it.luca.spring.enumeration.DatePattern;
import it.luca.spring.utils.Utils;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class IngestionAlertRecord {

    private final DataSourceId dataSourceId;
    private final LocalDateTime localDateTime = Utils.now();
    private final String localDate = Utils.now(DatePattern.DEFAULT_DATE);
    private final String exceptionClass;
    private final String exceptionMessage;

    public IngestionAlertRecord(DataSourceId dataSourceId, Exception exception) {

        this.dataSourceId = dataSourceId;
        this.exceptionClass = exception.getClass().getName();
        this.exceptionMessage = exception.getMessage();
    }
}
