CREATE TABLE IF NOT EXISTS @impala.db.name@.@impala.table.ingestionSuccess@ (

    message_ts TIMESTAMP COMMENT 'Timestamp of received message',
    message_dt STRING COMMENT 'Date of received message event (pattern yyyy-MM-dd)',
    datasource_id STRING,
    datasource_type STRING COMMENT 'JSON|XML',
    input_data_class STRING COMMENT 'FQ name of input data class',
    avro_record_class STRING COMMENT 'FQ name of Avro record class',
    topic_name STRING,
    topic_partition INT,
    message_offset LONG COMMENT 'Kafka offset of sent message',
    batch_record_count INT COMMENT 'Total number of Avro records brought by current batch',
    batch_record_prog INT COMMENT 'Progressive number of Avro record brought by current batch (ranges from 1 to batch_record_count)',
    insert_ts TIMESTAMP
)
PARTITIONED BY (insert_dt STRING COMMENT 'Date of record insert (pattern yyyy-MM-dd')
STORED AS PARQUET;