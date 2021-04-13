INSERT OVERWRITE ingestion_alert_log PARTITION(month)
SELECT * FROM ingestion_alert_log
WHERE month = from_timestamp(now(), 'yyyy-MM')