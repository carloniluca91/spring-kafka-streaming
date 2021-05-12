package it.luca.spring.data.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.SourceSpecification;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class ObjectDeserializer {

    private final static ObjectMapper jsonMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    private final static ObjectMapper xmlMapper = new XmlMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    public static <T> T readValue(InputStream inputStream, SourceSpecification<T, ?> specification) throws IOException {

        Class<T> valueType = specification.getInputDataClass();
        String className = specification.getInputDataClass().getSimpleName();
        DataSourceType dataSourceType = specification.getDataSourceType();
        log.info("Deserializing input {} content as instance of {}", dataSourceType, className);
        T payload = (dataSourceType == DataSourceType.JSON) ?
                jsonMapper.readValue(inputStream, valueType) :
                xmlMapper.readValue(inputStream, valueType);

        log.info("Deserialized input {} content as instance of {}", dataSourceType, className);
        return payload;
    }

    public static <T> T readValue(String content, SourceSpecification<T, ?> specification) throws IOException {

        Class<T> valueType = specification.getInputDataClass();
        String className = specification.getInputDataClass().getSimpleName();
        DataSourceType dataSourceType = specification.getDataSourceType();
        log.info("Deserializing input {} content as instance of {}", dataSourceType, className);
        T payload = (dataSourceType == DataSourceType.JSON) ?
                jsonMapper.readValue(content, valueType) :
                xmlMapper.readValue(content, valueType);

        log.info("Deserialized input {} content as instance of {}", dataSourceType, className);
        return payload;
    }
}
