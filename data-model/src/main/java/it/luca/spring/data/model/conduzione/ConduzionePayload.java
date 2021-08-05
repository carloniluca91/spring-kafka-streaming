package it.luca.spring.data.model.conduzione;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ConduzionePayload {

    private final List<ConduzioneRecord> records;

    @JsonCreator
    public ConduzionePayload(@JsonProperty("records") List<ConduzioneRecord> records) {

        this.records = records;
    }
}
