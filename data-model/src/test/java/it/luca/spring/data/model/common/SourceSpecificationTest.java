package it.luca.spring.data.model.common;

import org.apache.avro.specific.SpecificRecord;

import java.io.IOException;
import java.nio.file.Paths;

import static it.luca.spring.data.utils.ObjectDeserializer.readValue;

public abstract class SourceSpecificationTest<T, A extends SpecificRecord> {

    protected T instance;
    protected SourceSpecification<T, A> specification;

    public SourceSpecificationTest(String fileName, SourceSpecification<T, A> specification) throws IOException {

        String filePath = Paths.get("samples", fileName).toString();
        instance = readValue(getClass().getClassLoader().getResourceAsStream(filePath), specification);
        this.specification = specification;
    }

    public abstract void assertReadValue();

    public abstract void assertBuildAvroRecords();
}