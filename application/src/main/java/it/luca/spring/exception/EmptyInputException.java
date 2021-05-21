package it.luca.spring.exception;

import it.luca.spring.data.utils.DatePattern;

import static it.luca.spring.data.utils.Utils.now;

public class EmptyInputException extends Exception {

    public EmptyInputException() {
        super(String.format("Received empty input at %s", now(DatePattern.DEFAULT_TIMESTAMP)));
    }
}
