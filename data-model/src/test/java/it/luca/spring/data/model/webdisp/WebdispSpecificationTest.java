package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.common.SourceSpecificationTest;
import it.luca.spring.data.utils.DatePattern;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static it.luca.utils.time.Supplier.now;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WebdispSpecificationTest extends SourceSpecificationTest<WebdispPayload> {

    public WebdispSpecificationTest() {
        super("webdisp.xml", new WebdispSpecificationFactory().createInstance());
    }

    @Override
    protected void testSampleFileInstance(WebdispPayload instance) {

        instance.getNomine().forEach(webdispNomina -> {

            assertNotNull(webdispNomina.getPcs());
            assertNotNull(webdispNomina.getValoreEnergia());
            assertNotNull(webdispNomina.getUnitaMisuraEnergia());
            assertNotNull(webdispNomina.getValoreVolume());
            assertNotNull(webdispNomina.getUnitaMisuraVolume());
            assertNotNull(webdispNomina.getDataElaborazione());
            assertNotNull(webdispNomina.getDataDecorrenza());
            assertNotNull(webdispNomina.getCodiceRemi());
            assertNotNull(webdispNomina.getDescrizioneRemi());
            assertNotNull(webdispNomina.getDescrizionePunto());
            assertNotNull(webdispNomina.getTipoNomina());
            assertNotNull(webdispNomina.getCicloNomina());
            assertNotNull(webdispNomina.getTipologiaPunto());
        });
    }

    @Test
    @Override
    public void testValidation() {

        List<WebdispNomina> emptyNominas = new ArrayList<>();
        String dataOraInvio = now(DatePattern.DEFAULT_TIMESTAMP);
        WebdispNomina webdispNomina = new WebdispNomina(1d, 1d, "unitaMisuraEnergia", 1d,
                "unitaMisuraVolume", "dataElaborazione", "dataDecorrenza", "codiceRemi",
                "descrizioneRemi", "descrizionePunto", "tipoNomina", "cicloNomina",
                "tipologiaPunto");

        List<WebdispNomina> singletonNomina = Collections.singletonList(webdispNomina);

        testInstanceValidation(new WebdispPayload(null, null), false, 2);
        testInstanceValidation(new WebdispPayload(null, singletonNomina), false, 1);
        testInstanceValidation(new WebdispPayload(dataOraInvio, null), false, 1);
        testInstanceValidation(new WebdispPayload(dataOraInvio, emptyNominas), false, 1);
        testInstanceValidation(new WebdispPayload(dataOraInvio, Collections.singletonList(webdispNomina)), true, 0);
    }
}