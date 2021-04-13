package it.luca.spring.model.json.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JsonTestBean {

    private final Integer id;
    private final String firstName;
    private final String lastName;
}