INSERT INTO t_rw_conduzione_recover PARTITION(month)
VALUES (
:records.getBean.getVolumeStoccaggioCorrente,
:records.getBean.getVolumeIniettato,
:records.getBean.getValorePcsStoccaggio,
:records.getBean.getNumeroVersioneRecord,
:records.getBean.getNumeroRecordBatch,
:records.getBean.getDataElaborazione,
:records.getBean.getTipoAggiornamento,
:records.getBean.getCodiceCampo,
:records.getBean.getNomeCampo,
:records.getBean.getDataRiferimento,
:records.getBean.getVolumeConsumo,
:records.getBean.getVolumeStoccaggioTotale,
:records.getTsInsert,
:records:getDtInsert,
:records.getMonth
)