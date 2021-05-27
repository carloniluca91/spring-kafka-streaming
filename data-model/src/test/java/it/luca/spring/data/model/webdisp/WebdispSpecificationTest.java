package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.common.SourceSpecificationTest;
import it.luca.spring.data.utils.DatePattern;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import static it.luca.utils.time.TimeUtils.now;

class WebdispSpecificationTest extends SourceSpecificationTest<WebdispPayload> {

    public WebdispSpecificationTest() {
        super("webdisp.xml", new WebdispSpecification("topic"));
    }

    @Test
    @Override
    public void testSamples() {

        List<WebdispNomina> emptyNominas = new ArrayList<>();
        String dataOraInvio = now(DatePattern.DEFAULT_TIMESTAMP);
        WebdispNomina webdispNomina = new WebdispNomina(1d, 1d, "unitaMisuraEnergia", 1d,
                "unitaMisuraVolume", "dataElaborazione", "dataDecorrenza", "codiceRemi",
                "descrizioneRemi", "descrizionePunto", "tipoNomina", "cicloNomina",
                "tipologiaPunto");

        List<WebdispNomina> singletonNomina = Collections.singletonList(webdispNomina);
        BiFunction<String, List<WebdispNomina>, WebdispPayload> biFunction = WebdispPayload::new;

        testSample(biFunction.apply(null, null), false, 2);
        testSample(biFunction.apply(null, singletonNomina), false, 1);
        testSample(biFunction.apply(dataOraInvio, null), false, 1);
        testSample(biFunction.apply(dataOraInvio, emptyNominas), false, 1);
        testSample(biFunction.apply(dataOraInvio, Collections.singletonList(webdispNomina)), true, 1);
    }
}