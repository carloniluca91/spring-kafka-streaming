INSERT INTO ingestion_alert_log PARTITION(month)
VALUES (
:r.getDataSourceId,
:r.getLocalDateTime,
:r.getLocalDate,
:r.getExceptionClass,
:r.getExceptionMessage,
now(),
from_timestamp(now(), 'yyyy-MM-dd'),
from_timestamp(now(), 'yyyy-MM'))