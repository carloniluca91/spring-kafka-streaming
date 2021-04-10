package it.luca.cedacri.exception;

import it.luca.cedacri.enumeration.DatePattern;

import static it.luca.cedacri.utils.Utils.now;

public class EmptyInputException extends Exception {

    public EmptyInputException() {
        super(String.format("Received empty input at %s", now(DatePattern.DEFAULT_TIMESTAMP)));
    }
}
