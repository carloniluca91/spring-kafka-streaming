package it.luca.spring.data.webdisp;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;

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
    public WebdispNomina(@JacksonXmlProperty(localName = "pcs") Double pcs,
                         @JacksonXmlProperty(localName = "valoreEnergia") Double valoreEnergia,
                         @JacksonXmlProperty(localName = "unitaMisuraEnergia") String unitaMisuraEnergia,
                         @JacksonXmlProperty(localName = "valoreVolume") Double valoreVolume,
                         @JacksonXmlProperty(localName = "unitaMisuraVolume") String unitaMisuraVolume,
                         @JacksonXmlProperty(localName = "dataElaborazione") String dataElaborazione,
                         @JacksonXmlProperty(localName = "dataDecorrenza") String dataDecorrenza,
                         @JacksonXmlProperty(localName = "codiceRemi") String codiceRemi,
                         @JacksonXmlProperty(localName = "descrizioneRemi") String descrizioneRemi,
                         @JacksonXmlProperty(localName = "descrizionePunto") String descrizionePunto,
                         @JacksonXmlProperty(localName = "tipoNomina") String tipoNomina,
                         @JacksonXmlProperty(localName = "cicloNomina") String cicloNomina,
                         @JacksonXmlProperty(localName = "tipologiaPunto") String tipologiaPunto) {

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
