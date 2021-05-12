package it.luca.spring.model.json.core;

import it.luca.spring.utils.DataSourceId;
import it.luca.spring.utils.DatePattern;
import it.luca.spring.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MsgWrapper<T> {

    private final String messageDate = Utils.now(DatePattern.DEFAULT_DATE);
    private final LocalDateTime messageTimestamp = Utils.now();
    private final Long messageEpochSeconds = System.currentTimeMillis();
    private final DataSourceId dataSourceId;
    private final T payload;
}