package it.luca.spring.data.model.webdisp;

import it.luca.spring.data.enumeration.DataSourceId;
import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.model.common.SourceSpecification;
import it.luca.spring.data.utils.DatePattern;

import java.util.List;
import java.util.function.Function;

import static it.luca.spring.data.utils.Utils.map;
import static it.luca.spring.data.utils.Utils.now;

public class WebdispSpecification extends SourceSpecification<WebdispWrapper, WebdispAvro> {

    public WebdispSpecification() {
        super(DataSourceId.WEBDISP, DataSourceType.XML, WebdispWrapper.class, WebdispAvro.class, "");
    }

    @Override
    public List<WebdispAvro> getAvroRecords(WebdispWrapper input) {

        Function<WebdispNomina, WebdispAvro> function = x -> WebdispAvro.newBuilder()
                .setDataOraInvio(input.getDataOraInvio())
                .setPcs(x.getPcs())
                .setValoreEnergia(x.getValoreEnergia())
                .setUnitaMisuraEnergia(x.getUnitaMisuraEnergia())
                .setValoreVolume(x.getValoreVolume())
                .setUnitaMisuraVolume(x.getUnitaMisuraVolume())
                .setDataElaborazione(x.getDataElaborazione())
                .setDataDecorrenza(x.getDataDecorrenza())
                .setCodiceRemi(x.getCodiceRemi())
                .setDescrizioneRemi(x.getDescrizioneRemi())
                .setDescrizionePunto(x.getDescrizionePunto())
                .setTipoNomina(x.getTipoNomina())
                .setCicloNomina(x.getCicloNomina())
                .setTipologiaPunto(x.getTipologiaPunto())
                .setTsInserimento(now(DatePattern.DEFAULT_TIMESTAMP))
                .setDtInserimento(now(DatePattern.DEFAULT_DATE))
                .build();

        return map(input.getNomine(), function);
    }
}
