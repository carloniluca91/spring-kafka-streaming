package it.luca.spring.json.bancll01;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Bancll01Payload {

    private String dataOraInvio;
    private List<Bancll01Record> nomine;
}
