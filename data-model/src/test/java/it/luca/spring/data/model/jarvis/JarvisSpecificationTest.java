package it.luca.spring.data.model.jarvis;

import it.luca.spring.data.model.common.SourceSpecificationTest;
import it.luca.spring.data.utils.DatePattern;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import static it.luca.utils.time.Supplier.now;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JarvisSpecificationTest extends SourceSpecificationTest<JarvisPayload> {

    public JarvisSpecificationTest() { super("jarvis.xml", new JarvisSpecification("jarvis")); }

    @Override
    protected void testSampleFileInstance(JarvisPayload instance) {

        assertNotNull(instance.getAmbitoFlusso());
        assertNotNull(instance.getNomeFlusso());
        assertNotNull(instance.getImpresaMittente());
        assertNotNull(instance.getDataDiCreazione());
        assertNotNull(instance.getNumeroDati());
        assertNotNull(instance.getDataProcedura());
        assertNotNull(instance.getGiornoGas());
        assertNotNull(instance.getListaCicli());
        assertFalse(instance.getListaCicli().isEmpty());
        instance.getListaCicli().forEach(jarvisCiclo -> {

            assertNotNull(jarvisCiclo.getCicloDiRiferimento());
            assertNotNull(jarvisCiclo.getRinominaEnergia());
            assertNotNull(jarvisCiclo.getUnitaDiMisuraRinominaEnergia());
            assertNotNull(jarvisCiclo.getLimiteMinimoEnergia());
            assertNotNull(jarvisCiclo.getUnitaDiMisuraLimiteMinimoEnergia());
            assertNotNull(jarvisCiclo.getLimiteMassimoEnergia());
            assertNotNull(jarvisCiclo.getUnitaDiMisuraLimiteMassimoEnergia());
        });
    }

    @Test
    @Override
    public void testValidation() {

        List<JarvisCiclo> emptyCicli = new ArrayList<>();
        String giornoGas = now(DatePattern.DEFAULT_DATE);
        JarvisCiclo jarvisCiclo = new JarvisCiclo("cicloDiRiferimento", 1d,
                "unitaMisuraEnergiaRinomina",
                1d, "unitaMisuraEnergiaLimiteMinimo",
                1d, "unitaMisuraEnergiaLimiteMassimo");

        List<JarvisCiclo> singleTonCiclo = Collections.singletonList(jarvisCiclo);
        BiFunction<String, List<JarvisCiclo>, JarvisPayload> biFunction = (gGas, listaCicli) -> new JarvisPayload("ambitoFlusso",
                "nomeFlusso", "impresaMittente", "dataDiCreazione", 1,
                "dataProcedura", gGas, listaCicli);

        testInstanceValidation(biFunction.apply(null, null), false, 2);
        testInstanceValidation(biFunction.apply(null, singleTonCiclo), false, 1);
        testInstanceValidation(biFunction.apply(giornoGas, null), false, 1);
        testInstanceValidation(biFunction.apply(giornoGas, emptyCicli), false, 1);
        testInstanceValidation(biFunction.apply(giornoGas, singleTonCiclo), true, 0);

    }
}