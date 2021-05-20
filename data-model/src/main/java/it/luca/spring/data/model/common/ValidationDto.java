package it.luca.spring.data.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ValidationDto {

    private final Boolean valid;
    private final String message;
}
