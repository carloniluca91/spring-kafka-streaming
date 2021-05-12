CREATE TABLE IF NOT EXISTS t_rw_jarvis_recover (

    ambito_flusso STRING,
    nome_flusso STRING,
    impresa_mittente STRING,
    data_di_creazione STRING COMMENT 'Pattern dd/MM/yyyy HH:mm',
    numero_dati INT,
    data_procedura STRING COMMENT 'Pattern dd/MM/yyyy',
    giorno_gas STRING COMMENT 'Pattern dd/MM/yyyy',
    ciclo_di_riferimento INT,
    rinomina_energia STRING,
    unita_misura_energia STRING,
    limite_minimo_energia STRING,
    unita_misura_limite_minimo_energia STRING,
    limite_massimo_energia STRING,
    unita_misura_limite_massimo_energia STRING,
    ts_inserimento TIMESTAMP,
    dt_inserimento STRING COMMENT 'Pattern yyyy-MM-dd'
)
PARTITIONED BY (mese STRING COMMENT 'Pattern yyyy-MM')
STORED AS PARQUET