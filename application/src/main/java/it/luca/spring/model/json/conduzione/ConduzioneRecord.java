package it.luca.spring.model.json.conduzione;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConduzioneRecord {

    @JsonProperty("vstockCor")
    private String volumeStoccaggioCorrente;

    @JsonProperty("viniet")
    private String volumeIniettato;

    @JsonProperty("vpcsStocg")
    private String valorePcsStoccaggio;

    @JsonProperty("pver")
    private Integer numeroVersioneRecord;

    @JsonProperty("qrecTot")
    private Integer numeroRecordBatch;

    @JsonProperty("dre")
    private String dataElaborazione;

    private String tipoAggiornamento;

    @JsonProperty("ccmp")
    private String codiceCampo;

    @JsonProperty("ncmp")
    private String nomeCampo;

    @JsonProperty("drif")
    private String dataRiferimento;

    @JsonProperty("vcnsm")
    private String volumeConsumo;

    @JsonProperty("vstockTot")
    private String volumeStoccaggioTotale;

}

