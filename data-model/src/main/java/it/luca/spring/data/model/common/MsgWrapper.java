package it.luca.spring.data.model.common;

import it.luca.spring.data.utils.DatePattern;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static it.luca.spring.data.utils.Utils.now;

@Getter
@AllArgsConstructor
public class MsgWrapper<T> {

    private final String messageTs = now(DatePattern.DEFAULT_TIMESTAMP);
    private final String messageDt = now(DatePattern.DEFAULT_DATE);
    private final T payload;
}
