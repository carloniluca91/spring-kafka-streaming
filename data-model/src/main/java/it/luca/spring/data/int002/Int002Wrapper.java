package it.luca.spring.data.int002;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class Int002Wrapper {

    private final List<Int002Ciclo> cicli;

    @JsonCreator
    public Int002Wrapper(@JsonProperty("cicli") List<Int002Ciclo> cicli) {

        this.cicli = cicli;
    }
}
