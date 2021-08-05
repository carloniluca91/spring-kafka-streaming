package it.luca.spring.data.model.conduzione;

import it.luca.spring.data.model.common.SourceSpecificationTest;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConduzioneSpecificationTest extends SourceSpecificationTest<ConduzionePayload> {

    public ConduzioneSpecificationTest() {
        super("conduzione.json", new ConduzioneSpecificationFactory().getInstance());
    }

    @Override
    protected void testSampleFileInstance(ConduzionePayload instance) {

        assertNotNull(instance.getRecords());
        instance.getRecords().forEach(x -> {

            assertNotNull(x.getVstockCor());
            assertNotNull(x.getVprdn());
            assertNotNull(x.getViniet());
            assertNotNull(x.getVpcsStocg());
            assertNotNull(x.getVpcsRcp());
            assertNotNull(x.getPver());
            assertNotNull(x.getQrecTot());
            assertNotNull(x.getDre());
            assertNotNull(x.getTipoAggiornamento());
            assertNotNull(x.getCcmp());
            assertNotNull(x.getNcmp());
            assertNotNull(x.getDrif());
            assertNotNull(x.getVcnsm());
            assertNotNull(x.getVstockTot());
        });
    }

    @Override
    public void testValidation() {

        ConduzioneRecord record = new ConduzioneRecord(0d, 0d, 0d, 0d, 0d, 1, 13,
                "dre", "tipoAggiornamenti", "ccmp", "ncmp", "drif", 0d, 0d);

        testInstanceValidation(new ConduzionePayload(null), false, 1);
        testInstanceValidation(new ConduzionePayload(new ArrayList<>()), false, 1);
        testInstanceValidation(new ConduzionePayload(Collections.singletonList(record)), true, 0);
    }
}
