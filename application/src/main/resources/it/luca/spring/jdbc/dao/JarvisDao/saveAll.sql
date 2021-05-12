INSERT INTO t_rw_jarvis_recover PARTITION(month)
VALUES (
:ambitoFlusso,
:nomeFlusso,
:impresaMittente,
:dataDiCreazione,
:numeroDati,
:dataProcedura,
:giornoGas,
:records:getBean.getCicloDiRiferimento,
:records:getBean.getRinominaEnergia,
:records:getBean.getUnitaDiMisuraEnergiaRinomina,
:records:getBean.getLimiteMinimoEnergia,
:records:getBean.getUnitaDiMisuraEnergiaLimiteMinimo,
:records:getBean.getLimiteMassimoEnergia,
:records:getBean.getUnitaDiMisuraEnergiaLimiteMassimo,
:records.getTsInsert,
:records.getDtInsert,
:records.getMonth
)