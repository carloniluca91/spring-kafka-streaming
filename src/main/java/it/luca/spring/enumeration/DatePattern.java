package it.luca.spring.enumeration;

import lombok.Getter;

import java.time.format.DateTimeFormatter;

public enum DatePattern {

    DEFAULT_DATE("yyyy-MM-dd"),
    DEFAULT_TIMESTAMP("yyyy-MM-dd HH:mm:ss");

    @Getter private final String pattern;
    @Getter private final DateTimeFormatter formatter;

    DatePattern(String pattern) {

        this.pattern = pattern;
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }
}
