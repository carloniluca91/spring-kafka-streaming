-- create log table sequence
CREATE SEQUENCE IF NOT EXISTS @jdbc.log.table@_event_id;

-- set its start value to the count of old log table
SELECT setval('@jdbc.log.table@_event_id', (SELECT COUNT(*) FROM @jdbc.log.table@));

-- create new log table
CREATE TABLE IF NOT EXISTS @jdbc.log.table@_tmp (

    event_id INT NOT NULL DEFAULT NEXTVAL('@jdbc.log.table@_event_id'),
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

COMMENT ON COLUMN @jdbc.log.table@_tmp.event_id IS 'Id of message event (incremental)';
COMMENT ON COLUMN @jdbc.log.table@_tmp.event_ts IS 'Timestamp of received message event';
COMMENT ON COLUMN @jdbc.log.table@_tmp.event_dt IS 'Date of received message event';
COMMENT ON COLUMN @jdbc.log.table@_tmp.datasource_id IS 'Human readable datasource id';
COMMENT ON COLUMN @jdbc.log.table@_tmp.datasource_type IS 'JSON|XML';
COMMENT ON COLUMN @jdbc.log.table@_tmp.input_data_class IS 'FQ name of data class used for deserialization';
COMMENT ON COLUMN @jdbc.log.table@_tmp.ingestion_operation_code IS 'OK|KO';
COMMENT ON COLUMN @jdbc.log.table@_tmp.topic_name IS 'Topic where message has been published (null if KO)';
COMMENT ON COLUMN @jdbc.log.table@_tmp.topic_partition IS 'Partition where message has been published (null if KO)';
COMMENT ON COLUMN @jdbc.log.table@_tmp.message_offset IS 'Offset of published message (null if KO)';
COMMENT ON COLUMN @jdbc.log.table@_tmp.exception_class IS 'FQ name of exception class (null if OK)';
COMMENT ON COLUMN @jdbc.log.table@_tmp.exception_message IS 'Message of exception raised by ingestion process (null if OK)';
COMMENT ON COLUMN @jdbc.log.table@_tmp.insert_ts IS 'Timestamp of table record';
COMMENT ON COLUMN @jdbc.log.table@_tmp.insert_dt IS 'Date of table record';

-- insert data from old log table to new one
INSERT INTO @jdbc.log.table@_tmp (
event_id,
event_ts,
event_dt,
datasource_id,
datasource_type,
input_data_class,
ingestion_operation_code,
topic_name,
topic_partition,
message_offset,
exception_class,
exception_message,
insert_ts,
insert_dt
) SELECT
ROW_NUMBER() OVER (),
event_ts,
event_dt,
datasource_id,
datasource_type,
input_data_class,
ingestion_operation_code,
topic_name,
topic_partition,
message_offset,
exception_class,
exception_message,
insert_ts,
insert_dt
FROM @jdbc.log.table@
ORDER BY event_ts;

-- drop old log table
DROP TABLE IF EXISTS @jdbc.log.table@;

-- rename new log table
ALTER TABLE @jdbc.log.table@_tmp RENAME TO @jdbc.log.table@;