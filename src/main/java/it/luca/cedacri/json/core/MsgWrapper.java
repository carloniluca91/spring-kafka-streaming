package it.luca.cedacri.json.core;

import it.luca.cedacri.enumeration.DataSourceId;
import it.luca.cedacri.enumeration.DatePattern;
import it.luca.cedacri.utils.Utils;
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
