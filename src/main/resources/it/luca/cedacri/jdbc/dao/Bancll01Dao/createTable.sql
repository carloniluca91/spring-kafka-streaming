CREATE TABLE bancll01 (

    data_ora_invio STRING,
    id_nomina INT,
    nome_nomina STRING,
    ts_inserimento TIMESTAMP
)
PARTITIONED BY (dt_inserimento STRING)
STORED AS TEXTFILE