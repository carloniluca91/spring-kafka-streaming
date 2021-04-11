package it.luca.cedacri.jdbc.core;

import it.luca.cedacri.enumeration.DatePattern;
import it.luca.cedacri.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class JDBCRecord<T> {

    private final T bean;
    private final LocalDateTime tsInsert = Utils.now();
    private final String dtInsert = Utils.now(DatePattern.DEFAULT_DATE);
}
