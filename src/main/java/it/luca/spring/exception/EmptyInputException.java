package it.luca.spring.exception;

import it.luca.spring.utils.DatePattern;

import static it.luca.spring.utils.Utils.now;

public class EmptyInputException extends Exception {

    public EmptyInputException() {
        super(String.format("Received empty input at %s", now(DatePattern.DEFAULT_TIMESTAMP)));
    }
}
