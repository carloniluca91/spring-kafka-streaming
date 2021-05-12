package it.luca.spring.data.jarvis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class JarvisWrapper {

     private final String ambitoFlusso;
     private final String nomeFlusso;
     private final String impresaMittente;
     private final String dataDiCreazione;
     private final Integer numeroDati;
     private final String dataProcedura;
     private final String giornoGas;
     private final List<JarvisCiclo> listaCicli;

     @JsonCreator
     public JarvisWrapper(@JacksonXmlProperty(localName = "ambitoFlusso") String ambitoFlusso,
                          @JacksonXmlProperty(localName = "nomeFlusso") String nomeFlusso,
                          @JacksonXmlProperty(localName = "impresaMittente") String impresaMittente,
                          @JacksonXmlProperty(localName = "dataDiCreazione") String dataDiCreazione,
                          @JacksonXmlProperty(localName = "numeroDati") Integer numeroDati,
                          @JacksonXmlProperty(localName = "dataProcedura") String dataProcedura,
                          @JacksonXmlProperty(localName = "giornoGas") String giornoGas,
                          @JacksonXmlProperty(localName = "listaCicli") List<JarvisCiclo> listaCicli) {

         this.ambitoFlusso = ambitoFlusso;
         this.nomeFlusso = nomeFlusso;
         this.impresaMittente = impresaMittente;
         this.dataDiCreazione = dataDiCreazione;
         this.numeroDati = numeroDati;
         this.dataProcedura = dataProcedura;
         this.giornoGas = giornoGas;
         this.listaCicli = listaCicli;
     }
}
