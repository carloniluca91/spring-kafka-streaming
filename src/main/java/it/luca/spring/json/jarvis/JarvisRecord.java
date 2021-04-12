package it.luca.spring.json.jarvis;

import lombok.Data;

@Data
public class JarvisRecord {

    private Integer cicloDiRiferimento;
    private String rinominaEnergia;
    private String unitaDiMisuraEnergiaRinomina;
    private String limiteMinimoEnergia;
    private String unitaDiMisuraEnergiaLimiteMinimo;
    private String limiteMassimoEnergia;
    private String unitaDiMisuraEnergiaLimiteMassimo;

}
