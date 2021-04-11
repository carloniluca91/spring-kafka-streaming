INSERT INTO bancll01 PARTITION(dt_inserimento)
VALUES (:dataOraInvio,
:record.getBean.getId,
:record.getBean.getName,
:record.getTsInsert,
:record.getDtInsert
)