package it.luca.spring.data.int002;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class Int002Ciclo {

    private final String giornoOraRiferimento;
    private final String uDM1;
    private final String uDM2;
    private final String uDM3;
    private final String uDM4;
    private final String descrizione;
    private final String tipologia;
    private final String codiceRemi;
    private final Double valore1;
    private final Double progressivo1;
    private final Double valore2;
    private final Double progressivo2;
    private final Double PCS;
    private final Double valore3;
    private final Double progressivo3;
    private final Double valore4;
    private final Double progressivo4;
    private final Double pCS250;
    private final Double wobbe2515;
    private final Double wobbe250;

    @JsonCreator
    public Int002Ciclo(@JsonProperty("giornoOraRiferimento") String giornoOraRiferimento,
                       @JsonProperty("uDM1") String uDM1,
                       @JsonProperty("uDM2") String uDM2,
                       @JsonProperty("uDM3") String uDM3,
                       @JsonProperty("uDM4") String uDM4,
                       @JsonProperty("descrizione") String descrizione,
                       @JsonProperty("tipologia") String tipologia,
                       @JsonProperty("codiceRemi") String codiceRemi,
                       @JsonProperty("valore1") Double valore1,
                       @JsonProperty("progressivo1") Double progressivo1,
                       @JsonProperty("valore2") Double valore2,
                       @JsonProperty("progressivo2") Double progressivo2,
                       @JsonProperty("PCS") Double PCS,
                       @JsonProperty("valore3") Double valore3,
                       @JsonProperty("progressivo3") Double progressivo3,
                       @JsonProperty("valore4") Double valore4,
                       @JsonProperty("progressivo4") Double progressivo4,
                       @JsonProperty("pCS25_0") Double pCS250,
                       @JsonProperty("wobbe25_15") Double wobbe2515,
                       @JsonProperty("wobbe25_0") Double wobbe250) {

        this.giornoOraRiferimento = giornoOraRiferimento;
        this.uDM1 = uDM1;
        this.uDM2 = uDM2;
        this.uDM3 = uDM3;
        this.uDM4 = uDM4;
        this.descrizione = descrizione;
        this.tipologia = tipologia;
        this.codiceRemi = codiceRemi;
        this.valore1 = valore1;
        this.progressivo1 = progressivo1;
        this.valore2 = valore2;
        this.progressivo2 = progressivo2;
        this.PCS = PCS;
        this.valore3 = valore3;
        this.progressivo3 = progressivo3;
        this.valore4 = valore4;
        this.progressivo4 = progressivo4;
        this.pCS250 = pCS250;
        this.wobbe2515 = wobbe2515;
        this.wobbe250 = wobbe250;
    }
}
