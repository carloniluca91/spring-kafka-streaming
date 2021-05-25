package it.luca.spring.data.model.jarvis;

import it.luca.spring.data.model.common.SourceSpecificationTest;
import it.luca.spring.data.utils.DatePattern;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import static it.luca.spring.data.utils.Utils.now;

class JarvisSpecificationTest extends SourceSpecificationTest<JarvisPayload> {

    public JarvisSpecificationTest() { super("jarvis.xml", new JarvisSpecification("jarvis")); }

    @Test
    @Override
    public void testSamples() {

        List<JarvisCiclo> emptyCicli = new ArrayList<>();
        String giornoGas = now(DatePattern.DEFAULT_DATE);
        JarvisCiclo jarvisCiclo = new JarvisCiclo("cicloDiRiferimento", 1d,
                "unitaMisuraEnergiaRinomina",
                1d, "unitaMisuraEnergiaLimiteMinimo",
                1d, "unitaMisuraEnergiaLimiteMassimo");

        List<JarvisCiclo> singleTonCiclo = Collections.singletonList(jarvisCiclo);
        BiFunction<String, List<JarvisCiclo>, JarvisPayload> biFunction = (s, l) -> new JarvisPayload("ambitoFlusso",
                "nomeFlusso", "impresaMittente", "dataDiCreazione", 1,
                "dataProcedura", s, l);

        testSample(biFunction.apply(null, null), false, 2);
        testSample(biFunction.apply(null, singleTonCiclo), false, 1);
        testSample(biFunction.apply(giornoGas, null), false, 1);
        testSample(biFunction.apply(giornoGas, emptyCicli), false, 1);
        testSample(biFunction.apply(giornoGas, singleTonCiclo), true, 0);

    }
}