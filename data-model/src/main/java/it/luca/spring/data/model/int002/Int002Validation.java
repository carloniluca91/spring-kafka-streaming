package it.luca.spring.data.model.int002;

import it.luca.spring.data.model.validation.common.PojoValidation;
import it.luca.spring.data.model.validation.rules.NotEmptyListRule;

public class Int002Validation extends PojoValidation<Int002Payload> {

    public Int002Validation() {

        super(new NotEmptyListRule<>(Int002Payload::getCicli, "cicli"));
    }
}
