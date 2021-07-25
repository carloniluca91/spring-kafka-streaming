package it.luca.spring.data.model.int002;

import it.luca.spring.data.model.common.SourceSpecificationTest;
import it.luca.spring.data.utils.DatePattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static it.luca.utils.time.Supplier.now;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class Int002SpecificationTest extends SourceSpecificationTest<Int002Payload> {

    public Int002SpecificationTest() {

        super("int002.json", new Int002Specification());
    }

    @Override
    protected void testSampleFileInstance(Int002Payload instance) {

        assertNotNull(instance.getCicli());
        assertFalse(instance.getCicli().isEmpty());
        instance.getCicli().forEach(int002Ciclo -> {

            assertNotNull(int002Ciclo.getGiornoOraRiferimento());
            assertNotNull(int002Ciclo.getUDM1());
            assertNotNull(int002Ciclo.getUDM2());
            assertNotNull(int002Ciclo.getUDM3());
            assertNotNull(int002Ciclo.getUDM4());
            assertNotNull(int002Ciclo.getDescrizione());
            assertNotNull(int002Ciclo.getTipologia());
            assertNotNull(int002Ciclo.getCodiceRemi());
            assertNotNull(int002Ciclo.getValore1());
            assertNotNull(int002Ciclo.getProgressivo1());
            assertNotNull(int002Ciclo.getValore2());
            assertNotNull(int002Ciclo.getProgressivo2());
            assertNotNull(int002Ciclo.getPCS());
            assertNotNull(int002Ciclo.getValore3());
            assertNotNull(int002Ciclo.getProgressivo3());
            assertNotNull(int002Ciclo.getValore4());
            assertNotNull(int002Ciclo.getProgressivo4());
            assertNotNull(int002Ciclo.getPCS250());
            assertNotNull(int002Ciclo.getWobbe2515());
            assertNotNull(int002Ciclo.getWobbe250());
        });
    }

    @Override
    public void testValidation() {

        List<Int002Ciclo> emptyList = new ArrayList<>();
        Int002Ciclo int002Ciclo = new Int002Ciclo(now(DatePattern.DEFAULT_TIMESTAMP), "uDM1", "uDM2",
                "uDM3", "uDM4", "descrizione", "tipologia", "codiceRemi",
                1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d,
                1d, 1d, 1d, 1d);

        List<Int002Ciclo> nonEmptyList = Collections.singletonList(int002Ciclo);
        Function<List<Int002Ciclo>, Int002Payload> function = Int002Payload::new;
        testInstanceValidation(function.apply(emptyList), false, 1);
        testInstanceValidation(function.apply(nonEmptyList), true, 0);
    }
}