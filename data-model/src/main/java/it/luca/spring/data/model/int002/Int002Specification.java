package it.luca.spring.data.model.int002;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.SourceSpecification;

public class Int002Specification extends SourceSpecification<Int002Payload> {

    public Int002Specification(String topicName) {

        super(DataSourceId.INT002, DataSourceType.JSON, Int002Payload.class, new Int002Validation(), topicName);
    }
}
