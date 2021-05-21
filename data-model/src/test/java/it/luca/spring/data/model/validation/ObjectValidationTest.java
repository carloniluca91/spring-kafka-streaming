package it.luca.spring.data.model.validation;

import org.junit.jupiter.api.Test;

import static it.luca.spring.data.utils.Utils.isPresent;

class ObjectValidationTest {

    /*
    @Test
    public void validate() {

        TestBean testBean = new TestBean();
        ObjectValidation<TestBean> objectValidation = new ObjectValidation<>(
                new ValidationRule<>(x -> isPresent(x.getId()), new NullAttributeRationale("id")),
                new ValidationRule<>(x -> isPresent(x.getName()), new NullAttributeRationale("name")),
                new ValidationRule<>(x -> isPresent(x.getList()), new NullAttributeRationale("list"))
                new ValidationRule<>(x -> x.getList()), new EmptyListRationale("list"))
        ) {
            @Override
            public ValidationDto validate(TestBean input) {
                return super.validate(input);
            }
        };

    }

     */
}