package it.luca.spring.jdbc.dto;

import it.luca.spring.data.model.common.SourceSpecification;
import lombok.Getter;

import static it.luca.spring.data.utils.Utils.orNull;

@Getter
public class ErrorDto extends BaseDto {

    private final String exceptionClass;
    private final String exceptionMessage;

    public ErrorDto(SourceSpecification<?, ?> specification, Exception exception) {

        super(specification);
        exceptionClass = orNull(exception, e -> e.getClass().getName());
        exceptionMessage = orNull(exception, Exception::getMessage);
    }
}
