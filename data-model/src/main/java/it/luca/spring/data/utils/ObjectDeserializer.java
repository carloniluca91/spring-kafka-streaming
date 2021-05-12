package it.luca.spring.data.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import it.luca.spring.data.enumeration.DataSourceType;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ObjectDeserializer {

    private final static ObjectMapper jsonMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final static ObjectMapper xmlMapper = new XmlMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    /**
     * Deserializes given inputStream to an instance of T
     * @param inputStream [.xml | .json] file inputStream
     * @param valueType class of T
     * @param dataSourceType dataSourceType
     * @param <T> type of deserialized data
     * @return instance of T
     * @throws JsonProcessingException if deserialization fails
     */

    public static <T> T readValue(InputStream inputStream, Class<T> valueType, DataSourceType dataSourceType) throws IOException {

        String className = valueType.getSimpleName();
        log.info("Deserializing input {} content as instance of {}", dataSourceType, className);
        T payload = (dataSourceType == DataSourceType.JSON) ?
                jsonMapper.readValue(inputStream, valueType) :
                xmlMapper.readValue(inputStream, valueType);

        log.info("Deserialized input {} content as instance of {}", dataSourceType, className);
        return payload;
    }

    /**
     * Deserializes given string to an instance of T
     * @param content [XML | JSON] string
     * @param valueType class of T
     * @param dataSourceType dataSourceType [XML | JSON]
     * @param <T> type of deserialized data
     * @return instance of T
     * @throws JsonProcessingException if deserialization fails
     */

    public static <T> T readValue(String content, Class<T> valueType, DataSourceType dataSourceType) throws JsonProcessingException {

        String className = valueType.getSimpleName();
        log.info("Deserializing input {} content as instance of {}", dataSourceType, className);
        T payload = (dataSourceType == DataSourceType.JSON) ?
                jsonMapper.readValue(content, valueType) :
                xmlMapper.readValue(content, valueType);

        log.info("Deserialized input {} content as instance of {}", dataSourceType, className);
        return payload;
    }
}
