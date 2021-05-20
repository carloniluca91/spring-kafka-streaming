package it.luca.spring.data.model.common;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.enumeration.DataSourceType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class SourceSpecification<T> {

    protected final DataSourceId dataSourceId;
    protected final DataSourceType dataSourceType;
    protected final Class<T> inputDataClass;
    protected final String topicName;

    //public abstract ValidationDto validate(T input);
}
