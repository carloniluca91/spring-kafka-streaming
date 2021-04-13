package it.luca.spring.service;

import it.luca.spring.utils.DataSourceId;
import it.luca.spring.model.response.ResponseCode;
import it.luca.spring.model.response.SourceResponse;
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