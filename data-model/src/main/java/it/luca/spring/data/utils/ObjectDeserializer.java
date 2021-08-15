package it.luca.spring.data.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.DataSourceSpecification;
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
     * Deserialize input String as instance of T
     * @param inputStream input stream
     * @param specification specification for dataSource of dataType T
     * @param <T> instance type
     * @return instance of T
     * @throws IOException if deserialization fails
     */

    public static <T> T deserialize(InputStream inputStream, DataSourceSpecification<T> specification) throws IOException {

        Class<T> inputDataClass = specification.getDataClass();
        String className = inputDataClass.getSimpleName();
        DataSourceType dataSourceType = specification.getType();
        log.info("Deserializing input {} content as instance of {}", dataSourceType, className);
        T payload = (dataSourceType == DataSourceType.JSON) ?
                jsonMapper.readValue(inputStream, inputDataClass) :
                xmlMapper.readValue(inputStream, inputDataClass);

        log.info("Deserialized input {} content as instance of {}", dataSourceType, className);
        return payload;
    }

    /**
     * Deserialize input String as instance of T
     * @param content input string
     * @param specification specification for dataSource of dataType T
     * @param <T> instance type
     * @return instance of T
     * @throws IOException if deserialization fails
     */

    public static <T> T deserialize(String content, DataSourceSpecification<T> specification) throws IOException {

        Class<T> inputDataClass = specification.getDataClass();
        String className = inputDataClass.getSimpleName();
        DataSourceType dataSourceType = specification.getType();
        log.info("Deserializing input {} content as instance of {}", dataSourceType, className);
        T payload = (dataSourceType == DataSourceType.JSON) ?
                jsonMapper.readValue(content, inputDataClass) :
                xmlMapper.readValue(content, inputDataClass);

        log.info("Deserialized input {} content as instance of {}", dataSourceType, className);
        return payload;
    }
}
