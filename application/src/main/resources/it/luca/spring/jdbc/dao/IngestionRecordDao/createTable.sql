CREATE TABLE IF NOT EXISTS @impala.db.name@.@impala.table.ingestionLog@ (

    message_ts TIMESTAMP COMMENT 'Timestamp of received message',
    message_dt STRING COMMENT 'Date of received message event (pattern yyyy-MM-dd)',
    datasource_id STRING,
    datasource_type STRING COMMENT 'JSON|XML',
    input_data_class STRING COMMENT 'FQ name of input data class',
    topic_name STRING,
    topic_partition INT COMMENT 'Partition where message has been published (-1 in case of failure)',
    ingestion_operation_code STRING COMMENT 'OK if success, KO if failure',
    message_offset BIGINT COMMENT 'Offset of published message (-1 in case of failure)',
    exception_class STRING COMMENT 'FQ name of exception class',
    exception_message STRING,
    insert_ts TIMESTAMP
)
PARTITIONED BY (insert_dt STRING COMMENT 'Date of record insert (pattern yyyy-MM-dd')
STORED AS PARQUET;