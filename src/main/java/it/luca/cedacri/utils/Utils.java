package it.luca.cedacri.utils;

import it.luca.cedacri.enumeration.DatePattern;

import java.time.LocalDateTime;

public class Utils {

    public static LocalDateTime now() {

        return LocalDateTime.now();
    }

    public static String now(DatePattern datePattern) {

        return now().format(datePattern.getFormatter());
    }
}
