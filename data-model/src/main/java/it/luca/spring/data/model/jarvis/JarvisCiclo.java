package it.luca.spring.data.model.jarvis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;

@Getter
public class JarvisCiclo {

     private final String cicloDiRiferimento;
     private final Double rinominaEnergia;
     private final String unitaDiMisuraEnergiaRinomina;
     private final Double limiteMinimoEnergia;
     private final String unitaDiMisuraEnergiaLimiteMinimo;
     private final Double limiteMassimoEnergia;
     private final String unitaDiMisuraEnergiaLimiteMassimo;

     @JsonCreator
     public JarvisCiclo(@JacksonXmlProperty(localName = "cicloDiRiferimento") String cicloDiRiferimento,
                        @JacksonXmlProperty(localName = "rinominaEnergia") Double rinominaEnergia,
                        @JacksonXmlProperty(localName = "unitaDiMisuraEnergiaRinomina") String unitaDiMisuraEnergiaRinomina,
                        @JacksonXmlProperty(localName = "limiteMinimoEnergia") Double limiteMinimoEnergia,
                        @JacksonXmlProperty(localName = "unitaDiMisuraEnergiaLimiteMinimo") String unitaDiMisuraEnergiaLimiteMinimo,
                        @JacksonXmlProperty(localName = "limiteMassimoEnergia") Double limiteMassimoEnergia,
                        @JacksonXmlProperty(localName = "unitaDiMisuraEnergiaLimiteMassimo") String unitaDiMisuraEnergiaLimiteMassimo) {

         this.cicloDiRiferimento = cicloDiRiferimento;
         this.rinominaEnergia = rinominaEnergia;
         this.unitaDiMisuraEnergiaRinomina = unitaDiMisuraEnergiaRinomina;
         this.limiteMinimoEnergia = limiteMinimoEnergia;
         this.unitaDiMisuraEnergiaLimiteMinimo = unitaDiMisuraEnergiaLimiteMinimo;
         this.limiteMassimoEnergia = limiteMassimoEnergia;
         this.unitaDiMisuraEnergiaLimiteMassimo = unitaDiMisuraEnergiaLimiteMassimo;
     }
}
