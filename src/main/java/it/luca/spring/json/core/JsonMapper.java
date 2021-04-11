package it.luca.spring.json.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
public class JsonMapper {

    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T readValue(String content, Class<T> valueType) throws JsonProcessingException {

        String className = valueType.getSimpleName();
        log.info("Deserializing input content as instance of {}", className);
        T t = objectMapper.readValue(content, valueType);
        log.info("Deserialized input content as instance of {}", className);
        return t;
    }

    public static <T> T readValue(File src, Class<T> valueType) throws IOException {

        String className = valueType.getSimpleName();
        log.info("Deserializing input file as instance of {}", className);
        T t = objectMapper.readValue(src, valueType);
        log.info("Deserialized input file as instance of {}", className);
        return t;
    }
}
