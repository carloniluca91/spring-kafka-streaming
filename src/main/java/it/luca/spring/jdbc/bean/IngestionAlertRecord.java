package it.luca.spring.jdbc.bean;

import it.luca.spring.enumeration.DataSourceId;
import it.luca.spring.enumeration.DatePattern;
import it.luca.spring.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class IngestionAlertRecord {

    private final DataSourceId dataSourceId;
    private final LocalDateTime localDateTime = Utils.now();
    private final String localDate = Utils.now(DatePattern.DEFAULT_DATE);
    private final String exceptionClass;
    private final String exceptionMessage;
    private final boolean sentToKafkaTopic;
    private final boolean savedIntoImpalaTable;
}
