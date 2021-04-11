CREATE TABLE t_rw_bancll01_recovery (

    data_ora_invio STRING,
    id_nomina INT,
    nome_nomina STRING,
    ts_inserimento TIMESTAMP
)
PARTITIONED BY (dt_inserimento STRING)
STORED AS TEXTFILE