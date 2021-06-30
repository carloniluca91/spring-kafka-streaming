package it.luca.spring.data.model.jarvis;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Getter;

@Getter
public class JarvisCiclo {

    private final String cicloDiRiferimento;
    private final Double rinominaEnergia;
    private final String unitaDiMisuraRinominaEnergia;
    private final Double limiteMinimoEnergia;
    private final String unitaDiMisuraLimiteMinimoEnergia;
    private final Double limiteMassimoEnergia;
    private final String unitaDiMisuraLimiteMassimoEnergia;

     @JsonCreator
     public JarvisCiclo(@JacksonXmlProperty(localName = "cicloDiRiferimento") String cicloDiRiferimento,
                        @JacksonXmlProperty(localName = "rinominaEnergia") Double rinominaEnergia,
                        @JacksonXmlProperty(localName = "unitaDiMisuraRinominaEnergia") String unitaDiMisuraEnergiaRinomina,
                        @JacksonXmlProperty(localName = "limiteMinimoEnergia") Double limiteMinimoEnergia,
                        @JacksonXmlProperty(localName = "unitaDiMisuraLimiteMinimoEnergia") String unitaDiMisuraLimiteMinimoEnergia,
                        @JacksonXmlProperty(localName = "limiteMassimoEnergia") Double limiteMassimoEnergia,
                        @JacksonXmlProperty(localName = "unitaDiMisuraLimiteMassimoEnergia") String unitaDiMisuraLimiteMassimoEnergia) {

         this.cicloDiRiferimento = cicloDiRiferimento;
         this.rinominaEnergia = rinominaEnergia;
         this.unitaDiMisuraRinominaEnergia = unitaDiMisuraEnergiaRinomina;
         this.limiteMinimoEnergia = limiteMinimoEnergia;
         this.unitaDiMisuraLimiteMinimoEnergia = unitaDiMisuraLimiteMinimoEnergia;
         this.limiteMassimoEnergia = limiteMassimoEnergia;
         this.unitaDiMisuraLimiteMassimoEnergia = unitaDiMisuraLimiteMassimoEnergia;
     }
}
