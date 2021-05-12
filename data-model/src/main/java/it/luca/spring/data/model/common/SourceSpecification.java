package it.luca.spring.data.model.common;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.enumeration.DataSourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.avro.specific.SpecificRecord;

import java.util.List;

@Getter
@AllArgsConstructor
public abstract class SourceSpecification<T, A extends SpecificRecord> {

    protected final DataSourceId dataSourceId;
    protected final DataSourceType dataSourceType;
    protected final Class<T> inputDataClass;
    protected final Class<A> avroRecordClass;
    protected final String topicName;

    abstract public List<A> getAvroRecords(T input);
}
