package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.model.common.SourceSpecificationTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class WebdispSpecificationTest extends SourceSpecificationTest<WebdispWrapper, WebdispAvro> {

    public WebdispSpecificationTest() throws IOException {
        super("webdisp.xml", new WebdispSpecification("topic"));
    }

    @Test
    @Override
    public void assertReadValue() {

        assertNotNull(instance);
        assertNotNull(instance.getDataOraInvio());
        assertNotNull(instance.getNomine());
        assertFalse(instance.getNomine().isEmpty());
    }

    @Test
    @Override
    public void assertBuildAvroRecords() {

        List<WebdispNomina> webdispNominas = instance.getNomine();
        List<WebdispAvro> webdispAvros = specification.getAvroRecords(instance);
        assertEquals(webdispNominas.size(), webdispAvros.size());
        IntStream.range(0, webdispNominas.size())
                .forEach(i -> {

                    WebdispNomina webdispNomina = webdispNominas.get(i);
                    WebdispAvro webdispAvro = webdispAvros.get(i);
                    assertEquals(instance.getDataOraInvio(), webdispAvro.getDataOraInvio());
                    assertEquals(webdispNomina.getPcs(), webdispAvro.getPcs());
                    assertEquals(webdispNomina.getValoreEnergia(), webdispAvro.getValoreEnergia());
                    assertEquals(webdispNomina.getUnitaMisuraEnergia(), webdispAvro.getUnitaMisuraEnergia());
                    assertEquals(webdispNomina.getValoreVolume(), webdispAvro.getValoreVolume());
                    assertEquals(webdispNomina.getUnitaMisuraVolume(), webdispAvro.getUnitaMisuraVolume());
                    assertEquals(webdispNomina.getDataElaborazione(), webdispAvro.getDataElaborazione());
                    assertEquals(webdispNomina.getDataDecorrenza(), webdispAvro.getDataDecorrenza());
                    assertEquals(webdispNomina.getCodiceRemi(), webdispAvro.getCodiceRemi());
                    assertEquals(webdispNomina.getDescrizioneRemi(), webdispAvro.getDescrizioneRemi());
                    assertEquals(webdispNomina.getDescrizionePunto(), webdispAvro.getDescrizionePunto());
                    assertEquals(webdispNomina.getTipoNomina(), webdispAvro.getTipoNomina());
                    assertEquals(webdispNomina.getCicloNomina(), webdispAvro.getCicloNomina());
                    assertEquals(webdispNomina.getTipologiaPunto(), webdispAvro.getTipologiaPunto());
                });
    }
}