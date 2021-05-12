CREATE TABLE IF NOT EXISTS @impala.db.name@.@impala.table.ingestionError@ (

    datasource_id STRING,
    datasource_type STRING COMMENT 'JSON|XML',
    input_data_class STRING COMMENT 'FQ name of input data class',
    avro_record_class STRING COMMENT 'FQ name of Avro record class',
    topic_name STRING,
    message_ts TIMESTAMP COMMENT 'Timestamp of received message',
    message_dt STRING COMMENT 'Date of received message event (pattern yyyy-MM-dd)',
    exception_class STRING COMMENT 'FQ name of exception class',
    exception_message STRING,
    insert_ts TIMESTAMP
)
PARTITIONED BY (insert_dt STRING COMMENT 'Date of record insert (pattern yyyy-MM-dd')
STORED AS PARQUET;