package it.luca.spring.data.model.int002;

/*
import it.luca.streaming.data.model.common.SourceSpecificationTest;
import it.luca.streaming.data.utils.DatePattern;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static it.luca.streaming.data.utils.Utils.*;
import static org.junit.jupiter.api.Assertions.*;

class Int002SpecificationTest extends SourceSpecificationTest<Int002Wrapper, Int002Avro, String> {

    private final Function<Int002Ciclo, String> gasDay = x ->
            gasDay(x.getGiornoOraRiferimento(), DatePattern.INT002_GIORNO_ORA_RIFERIMENTO);

    public Int002SpecificationTest() throws IOException {
        super("int002.json", new Int002Specification());
    }

    @Test
    @Override
    public void assertReadValue() {

        assertNotNull(instance);
        assertNotNull(instance.getCicli());
        assertFalse(instance.getCicli().isEmpty());
    }

    @Test
    @Override
    public void getDistinctPartitionValues() {

        List<String> expectedPartitionValues = map(instance.getCicli(), gasDay)
                .stream().distinct()
                .collect(Collectors.toList());

        List<String> actualPartitionValues = specification.getDistinctPartitionValues(instance);
        assertFalse(actualPartitionValues.isEmpty());
        assertEquals(expectedPartitionValues.size(), actualPartitionValues.size());
        expectedPartitionValues.forEach(x -> assertTrue(actualPartitionValues.contains(x)));
    }

    @Test
    @Override
    public void getPartitionRecordsMap() {

        Map<String, List<Int002Avro>> map = specification.getPartitionRecordsMap(instance);
        map.forEach(((s, int002Avros) -> {

            List<Int002Ciclo> int002Ciclos = filter(instance.getCicli(), x -> gasDay.apply(x).equals(s))
                    .stream().sorted(Comparator.comparing(Int002Ciclo::getGiornoOraRiferimento))
                    .collect(Collectors.toList());

            List<Int002Avro> sortedAvros = map.get(s)
                    .stream().sorted(Comparator.comparing(Int002Avro::getGiornoOraRiferimento))
                    .collect(Collectors.toList());

            assertEquals(int002Ciclos.size(), sortedAvros.size());
            IntStream.range(0, int002Ciclos.size())
                    .forEach(i -> {

                        Int002Ciclo int002Ciclo = int002Ciclos.get(i);
                        Int002Avro int002Avro = sortedAvros.get(i);

                        assertEquals(int002Ciclo.getGiornoOraRiferimento(), int002Avro.getGiornoOraRiferimento());
                        assertEquals(int002Ciclo.getUDM1(), int002Avro.getUDM1());
                        assertEquals(int002Ciclo.getUDM2(), int002Avro.getUDM2());
                        assertEquals(int002Ciclo.getUDM3(), int002Avro.getUDM3());
                        assertEquals(int002Ciclo.getUDM4(), int002Avro.getUDM4());
                        assertEquals(int002Ciclo.getDescrizione(), int002Avro.getDescrizione());
                        assertEquals(int002Ciclo.getTipologia(), int002Avro.getTipologia());
                        assertEquals(int002Ciclo.getCodiceRemi(), int002Avro.getCodiceRemi());
                        assertEquals(int002Ciclo.getValore1(), int002Avro.getValore1());
                        assertEquals(int002Ciclo.getProgressivo1(), int002Avro.getProgressivo1());
                        assertEquals(int002Ciclo.getValore2(), int002Avro.getValore2());
                        assertEquals(int002Ciclo.getProgressivo2(), int002Avro.getProgressivo2());
                        assertEquals(int002Ciclo.getPCS(), int002Avro.getPCS());
                        assertEquals(int002Ciclo.getValore3(), int002Avro.getValore3());
                        assertEquals(int002Ciclo.getProgressivo3(), int002Avro.getProgressivo3());
                        assertEquals(int002Ciclo.getValore4(), int002Avro.getValore4());
                        assertEquals(int002Ciclo.getProgressivo4(), int002Avro.getProgressivo4());
                        assertEquals(int002Ciclo.getPCS250(), int002Avro.getPCS250());
                        assertEquals(int002Ciclo.getWobbe2515(), int002Avro.getWobbe2515());
                        assertEquals(int002Ciclo.getWobbe250(), int002Avro.getWobbe250());
                    });
        }));
    }
}

 */