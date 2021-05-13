package it.luca.spring.data.model.jarvis;

import it.luca.spring.data.model.common.SourceSpecificationTest;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static it.luca.spring.data.utils.Utils.changeDatePattern;
import static org.junit.jupiter.api.Assertions.*;

/*
class JarvisSpecificationTest extends SourceSpecificationTest<JarvisWrapper, JarvisAvro> {

    public JarvisSpecificationTest() throws IOException {

        super("jarvis.xml", new JarvisSpecification());
    }

    @Override
    public void assertReadValue() {

        assertNotNull(instance);
        assertNotNull(instance.getAmbitoFlusso());
        assertNotNull(instance.getNomeFlusso());
        assertNotNull(instance.getImpresaMittente());
        assertNotNull(instance.getDataDiCreazione());
        assertNotNull(instance.getNumeroDati());
        assertNotNull(instance.getDataProcedura());
        assertNotNull(instance.getGiornoGas());
        assertNotNull(instance.getListaCicli());
        assertFalse(instance.getListaCicli().isEmpty());
    }

    @Test
    @Override
    public void getDistinctPartitionValues() {

        List<String> list = specification.getDistinctPartitionValues(instance);
        assertFalse(list.isEmpty());
        assertEquals(1, list.size());
        assertEquals(changeDatePattern(instance.getGiornoGas(), DatePattern.JARVIS_GIORNO_GAS, DatePattern.DEFAULT_DATE), list.get(0));
    }

    @Test
    @Override
    public void getPartitionRecordsMap() {

        Map<String, List<JarvisAvro>> map = specification.getPartitionRecordsMap(instance);
        assertFalse(map.isEmpty());
        assertEquals(1, map.size());

        String key = changeDatePattern(instance.getGiornoGas(), DatePattern.JARVIS_GIORNO_GAS, DatePattern.DEFAULT_DATE);
        assertTrue(map.containsKey(key));
        IntStream.range(0, map.size())
                .forEach(i -> {

                    JarvisCiclo jarvisCiclo = instance.getListaCicli().get(i);
                    JarvisAvro jarvisAvro = map.get(key).get(i);
                    assertEquals(instance.getAmbitoFlusso(), jarvisAvro.getAmbitoFlusso());
                    assertEquals(instance.getNomeFlusso(), jarvisAvro.getNomeFlusso());
                    assertEquals(instance.getImpresaMittente(), jarvisAvro.getImpresaMittente());
                    assertEquals(instance.getDataDiCreazione(), jarvisAvro.getDataDiCreazione());
                    assertEquals(instance.getNumeroDati(), jarvisAvro.getNumeroDati());
                    assertEquals(instance.getDataProcedura(), jarvisAvro.getDataProcedura());
                    assertEquals(jarvisCiclo.getCicloDiRiferimento(), jarvisAvro.getCicloDiRiferimento());
                    assertEquals(jarvisCiclo.getRinominaEnergia(), jarvisAvro.getRinominaEnergia());
                    assertEquals(jarvisCiclo.getUnitaDiMisuraEnergiaRinomina(), jarvisAvro.getUnitaDiMisuraEnergiaRinomina());
                    assertEquals(jarvisCiclo.getLimiteMinimoEnergia(), jarvisAvro.getLimiteMinimoEnergia());
                    assertEquals(jarvisCiclo.getUnitaDiMisuraEnergiaLimiteMinimo(), jarvisAvro.getUnitaDiMisuraEnergiaLimiteMinimo());
                    assertEquals(jarvisCiclo.getLimiteMassimoEnergia(), jarvisAvro.getLimiteMassimoEnergia());
                    assertEquals(jarvisCiclo.getUnitaDiMisuraEnergiaLimiteMassimo(), jarvisAvro.getUnitaDiMisuraEnergiaLimiteMassimo());
                    assertNotNull(jarvisAvro.getTsInserimento());
                    assertNotNull(jarvisAvro.getDtInserimento());
                });
    }
}

 */