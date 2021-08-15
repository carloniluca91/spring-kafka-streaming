package it.luca.spring.data.model.int002;

import it.luca.spring.data.model.common.PojoValidation;
import it.luca.spring.data.model.common.SampleValidator;

public class Int002SampleValidator implements SampleValidator<Int002Payload> {

    @Override
    public PojoValidation<Int002Payload> create() {

        return PojoValidation.<Int002Payload>builder()
                .withNotNullOrEmptyListCheck("cicli", Int002Payload::getCicli)
                .build();
    }
}
