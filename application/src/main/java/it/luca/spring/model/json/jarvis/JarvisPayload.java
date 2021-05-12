package it.luca.spring.model.json.jarvis;

import lombok.Data;

import java.util.List;

@Data
public class JarvisPayload {

    private String ambitoFlusso;
    private String nomeFlusso;
    private String impresaMittente;
    private String dataDiCreazione;
    private Integer numeroDati;
    private String dataProcedura;
    private String giornoGas;
    private List<JarvisRecord> listaCicli;
}
