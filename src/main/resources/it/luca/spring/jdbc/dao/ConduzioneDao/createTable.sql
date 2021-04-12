CREATE TABLE t_rw_conduzione_recover (

    volume_stoccaggio_corrente STRING,
    volume_iniettato STRING,
    valore_pcs_stoccaggio STRING,
    numero_versione_record INT,
    numero_record_batch INT,
    data_elaborazione STRING COMMENT 'Pattern yyyy-MM-dd HH:mm:ss.0',
    tipo_aggiornamento STRING,
    codice_campo STRING,
    nome_campo STRING,
    data_riferimento STRING COMMENT 'Pattern yyyy-MM-dd HH:mm:ss.0',
    volume_consumo STRING,
    volume_stoccaggio_totale STRING,
    ts_inserimento TIMESTAMP,
    dt_inserimento STRING COMMENT 'Pattern yyyy-MM-dd'
)
PARTITIONED BY (mese STRING COMMENT 'Pattern yyyy-MM')
STORED AS PARQUET