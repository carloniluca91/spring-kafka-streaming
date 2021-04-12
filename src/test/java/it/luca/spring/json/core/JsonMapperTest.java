package it.luca.spring.json.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonMapperTest {

    @Test
    void readValue() throws JsonProcessingException {

        Integer ID = 1;
        String FIRST_NAME = "hello";
        String LAST_NAME = "world";

        JsonTestBean jsonTestBean = new JsonTestBean(ID, FIRST_NAME, LAST_NAME);
        String jsonString = new ObjectMapper().writeValueAsString(jsonTestBean);
        JsonTestBean deserializedBean = JsonMapper.readValue(jsonString, JsonTestBean.class);
        assertEquals(ID, deserializedBean.getId());
        assertEquals(FIRST_NAME, deserializedBean.getFirstName());
        assertEquals(LAST_NAME, deserializedBean.getLastName());
    }
}