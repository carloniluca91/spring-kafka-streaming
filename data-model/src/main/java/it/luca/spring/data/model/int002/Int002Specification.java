package it.luca.spring.data.model.int002;

import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.SourceSpecification;

public class Int002Specification extends SourceSpecification<Int002Payload> {

    public Int002Specification() {

        super("INT002", Int002Payload.class, DataSourceType.JSON, new Int002Validation());
    }
}
