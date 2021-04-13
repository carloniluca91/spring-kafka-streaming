package it.luca.spring.model.json.conduzione;

import lombok.Data;

import java.util.List;

@Data
public class ConduzionePayload {

    private List<ConduzioneRecord> samples;
}
