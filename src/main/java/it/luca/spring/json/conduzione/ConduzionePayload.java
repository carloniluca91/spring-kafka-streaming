package it.luca.spring.json.conduzione;

import lombok.Data;

import java.util.List;

@Data
public class ConduzionePayload {

    private List<ConduzioneRecord> rilevamenti;
}
