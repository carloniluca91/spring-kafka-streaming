package it.luca.spring.service;

import it.luca.spring.enumeration.DataSourceId;
import it.luca.spring.enumeration.ResponseCode;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SourceResponseTest {

    @Test
    void init() {

        SourceResponse noExceptionResponse = new SourceResponse(DataSourceId.CONDUZIONE, Optional.empty());
        assertEquals(ResponseCode.OK, noExceptionResponse.getResponseCode());
    }
}