package it.luca.spring.data.model.webdisp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import it.luca.spring.data.utils.XMLField;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class WebdispNomina {

    private final Double pcs;
    private final Double valoreEnergia;
    private final String unitaMisuraEnergia;
    private final Double valoreVolume;
    private final String unitaMisuraVolume;
    private final String dataElaborazione;
    private final String dataDecorrenza;
    private final String codiceRemi;
    private final String descrizioneRemi;
    private final String descrizionePunto;
    private final String tipoNomina;
    private final String cicloNomina;
    private final String tipologiaPunto;

    @JsonCreator
    public WebdispNomina(@JacksonXmlProperty(localName = XMLField.PCS) Double pcs,
                         @JacksonXmlProperty(localName = XMLField.VALORE_ENERGIA) Double valoreEnergia,
                         @JacksonXmlProperty(localName = XMLField.UNITA_MISURA_ENERGIA) String unitaMisuraEnergia,
                         @JacksonXmlProperty(localName = XMLField.VALORE_VOLUME) Double valoreVolume,
                         @JacksonXmlProperty(localName = XMLField.UNITA_MISURA_VOLUME) String unitaMisuraVolume,
                         @JacksonXmlProperty(localName = XMLField.DATA_ELABORAZIONE) String dataElaborazione,
                         @JacksonXmlProperty(localName = XMLField.DATA_DECORRENZA) String dataDecorrenza,
                         @JacksonXmlProperty(localName = XMLField.CODICE_REMI) String codiceRemi,
                         @JacksonXmlProperty(localName = XMLField.DESCRIZIONE_REMI) String descrizioneRemi,
                         @JacksonXmlProperty(localName = XMLField.DESCRIZIONE_PUNTO) String descrizionePunto,
                         @JacksonXmlProperty(localName = XMLField.TIPO_NOMINA) String tipoNomina,
                         @JacksonXmlProperty(localName = XMLField.CICLO_NOMINA) String cicloNomina,
                         @JacksonXmlProperty(localName = XMLField.TIPOLOGIA_PUNTO) String tipologiaPunto) {

        this.pcs = pcs;
        this.valoreEnergia = valoreEnergia;
        this.unitaMisuraEnergia = unitaMisuraEnergia;
        this.valoreVolume = valoreVolume;
        this.unitaMisuraVolume = unitaMisuraVolume;
        this.dataElaborazione = dataElaborazione;
        this.dataDecorrenza = dataDecorrenza;
        this.codiceRemi = codiceRemi;
        this.descrizioneRemi = descrizioneRemi;
        this.descrizionePunto = descrizionePunto;
        this.tipoNomina = tipoNomina;
        this.cicloNomina = cicloNomina;
        this.tipologiaPunto = tipologiaPunto;
    }
}
