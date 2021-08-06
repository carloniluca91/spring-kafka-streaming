-- log table
CREATE TABLE IF NOT EXISTS @jdbc.log.table@ (

    event_ts TIMESTAMP NOT NULL DEFAULT NOW(),
    event_dt DATE NOT NULL DEFAULT NOW()::DATE,
    datasource_id TEXT NOT NULL,
    datasource_type TEXT NOT NULL,
    input_data_class TEXT NOT NULL,
    ingestion_operation_code TEXT NOT NULL,
    topic_name TEXT,
    topic_partition INT,
    message_offset INT,
    exception_class TEXT,
    exception_message TEXT,
    insert_ts TIMESTAMP NOT NULL DEFAULT NOW(),
    insert_dt DATE NOT NULL DEFAULT NOW()::DATE
);

COMMENT ON COLUMN @jdbc.log.table@.event_ts IS 'Timestamp of received message event';
COMMENT ON COLUMN @jdbc.log.table@.event_dt IS 'Date of received message event';
COMMENT ON COLUMN @jdbc.log.table@.datasource_id IS 'Human readable datasource id';
COMMENT ON COLUMN @jdbc.log.table@.datasource_type IS 'JSON|XML';
COMMENT ON COLUMN @jdbc.log.table@.input_data_class IS 'FQ name of data class used for deserialization';
COMMENT ON COLUMN @jdbc.log.table@.ingestion_operation_code IS 'OK|KO';
COMMENT ON COLUMN @jdbc.log.table@.topic_name IS 'Topic where message has been published (null if KO)';
COMMENT ON COLUMN @jdbc.log.table@.topic_partition IS 'Partition where message has been published (null if KO)';
COMMENT ON COLUMN @jdbc.log.table@.message_offset IS 'Offset of published message (null if KO)';
COMMENT ON COLUMN @jdbc.log.table@.exception_class IS 'FQ name of exception class (null if OK)';
COMMENT ON COLUMN @jdbc.log.table@.exception_message IS 'Message of exception raised by ingestion process (null if OK)';
COMMENT ON COLUMN @jdbc.log.table@.insert_ts IS 'Timestamp of table record';
COMMENT ON COLUMN @jdbc.log.table@.insert_dt IS 'Date of table record';
