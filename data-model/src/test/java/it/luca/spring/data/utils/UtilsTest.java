package it.luca.spring.data.utils;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {

    private final String INPUT = "hello";

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