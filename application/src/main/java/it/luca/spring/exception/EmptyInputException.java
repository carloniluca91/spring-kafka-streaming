package it.luca.spring.exception;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.utils.DatePattern;

import static it.luca.spring.data.utils.Utils.now;

public class EmptyInputException extends Exception {

    public EmptyInputException(DataSourceId dataSourceId) {
        super(String.format("(%s) Received empty input at %s", dataSourceId, now(DatePattern.DEFAULT_TIMESTAMP)));
    }
}
