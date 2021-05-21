package it.luca.spring.data.model.validation;

import lombok.Data;

import java.util.List;

@Data
public class TestBean {

    private String name;
    private Integer id;
    private List<String> list;
}
