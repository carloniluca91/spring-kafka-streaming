package it.luca.spring.data.model.conduzione;

import it.luca.spring.data.model.common.PojoValidation;
import it.luca.spring.data.model.common.SampleValidator;

public class ConduzioneSampleValidator implements SampleValidator<ConduzionePayload> {

    @Override
    public PojoValidation<ConduzionePayload> create() {

        return PojoValidation.<ConduzionePayload>builder()
                .withNotNullOrEmptyListCheck("records", ConduzionePayload::getRecords)
                .build();
    }
}
