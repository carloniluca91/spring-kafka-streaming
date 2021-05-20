package it.luca.spring.data.model.common;

import java.io.IOException;
import java.nio.file.Paths;

import static it.luca.spring.data.utils.ObjectDeserializer.readValue;

public abstract class SourceSpecificationTest<T> {

    protected T instance;
    protected SourceSpecification<T> specification;

    public SourceSpecificationTest(String fileName, SourceSpecification<T> specification) throws IOException {

        String filePath = Paths.get("samples", fileName).toString();
        instance = readValue(getClass().getClassLoader().getResourceAsStream(filePath), specification);
        this.specification = specification;
    }

    public abstract void assertReadValue();
}