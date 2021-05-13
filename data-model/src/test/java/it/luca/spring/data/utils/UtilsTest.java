package it.luca.spring.data.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.BiConsumer;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    private final String INPUT = "hello";

    @Test
    void gasDay() {

        BiConsumer<LocalDateTime, LocalDateTime> consumer = ((input, expected) -> {

            String inputString = input.format(DateTimeFormatter.ofPattern(DatePattern.DEFAULT_TIMESTAMP));
            String expectation = expected.format(DateTimeFormatter.ofPattern(DatePattern.DEFAULT_DATE));
            assertEquals(expectation, Utils.gasDay(inputString, DatePattern.DEFAULT_TIMESTAMP));
        });

        final LocalDateTime firstLocalDatetime = LocalDateTime.of(2021, 1, 1, 5, 50, 0);
        consumer.accept(firstLocalDatetime, firstLocalDatetime.minusDays(1));
        consumer.accept(firstLocalDatetime.plusMinutes(20), firstLocalDatetime);
    }

    @Test
    void isPresent() {

        assertTrue(Utils.isPresent(INPUT));
        assertFalse(Utils.isPresent((String) null));
    }

    @Test
    void orElse() {

        final String ELSE_VALUE = "world";
        Function<String, String> function = String::toUpperCase;
        assertEquals(function.apply(INPUT), Utils.orElse(INPUT, function, ELSE_VALUE));
        assertEquals(ELSE_VALUE, Utils.orElse(null, function, ELSE_VALUE));
    }

    @Test
    void orNull() {

        Function<String, String> function = String::toUpperCase;
        assertEquals(function.apply(INPUT), Utils.orNull(INPUT, function));
        assertNull(Utils.orNull(null, function));
    }
}