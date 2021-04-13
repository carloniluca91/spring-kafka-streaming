package it.luca.spring.model.jdbc;

import it.luca.spring.utils.DatePattern;
import it.luca.spring.utils.Utils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class JDBCRecord<T> {

    private final T bean;
    private final LocalDateTime tsInsert = Utils.now();
    private final String dtInsert = Utils.now(DatePattern.DEFAULT_DATE);
    private final String month = Utils.now(DatePattern.DEFAULT_MONTH);
}