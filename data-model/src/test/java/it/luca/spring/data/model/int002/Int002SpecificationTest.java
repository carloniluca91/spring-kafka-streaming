package it.luca.spring.data.model.int002;

import it.luca.spring.data.model.common.SourceSpecificationTest;
import it.luca.spring.data.utils.DatePattern;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

import static it.luca.utils.time.TimeUtils.now;

class Int002SpecificationTest extends SourceSpecificationTest<Int002Payload> {

    public Int002SpecificationTest() {

        super("int002.json", new Int002Specification("jarvis"));
    }

    @Test
    @Override
    public void testSamples() {

        List<Int002Ciclo> emptyList = new ArrayList<>();
        Int002Ciclo int002Ciclo = new Int002Ciclo(now(DatePattern.DEFAULT_TIMESTAMP), "uDM1", "uDM2",
                "uDM3", "uDM4", "descrizione", "tipologia", "codiceRemi",
                1d, 1d, 1d, 1d, 1d, 1d, 1d, 1d,
                1d, 1d, 1d, 1d);

        List<Int002Ciclo> nonEmptyList = Collections.singletonList(int002Ciclo);
        Function<List<Int002Ciclo>, Int002Payload> function = Int002Payload::new;
        testSample(function.apply(emptyList), false, 1);
        testSample(function.apply(nonEmptyList), true, 0);

    }
}