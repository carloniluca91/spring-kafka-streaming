CREATE TABLE IF NOT EXISTS _ingestion_alert (

    datasource STRING COMMENT 'DataSource Id',
    ts_alert TIMESTAMP COMMENT 'Alert Timestamp',
    dt_alert STRING COMMENT 'Alert Date (format yyyy-MM-dd)',
    alert_exception_class STRING COMMENT 'Alert Exception FQ Class Name',
    alert_exception_message STRING COMMENT 'Alert Exception Message',
    ts_insert TIMESTAMP COMMENT 'Record Insert Timestamp',
    dt_insert TIMESTAMP COMMENT 'Record Insert Date (format yyyy-MM-dd)',
)
PARTITIONED BY (month STRING COMMENT 'Record Insert Month (format yyyy-MM)')
STORED AS PARQUET