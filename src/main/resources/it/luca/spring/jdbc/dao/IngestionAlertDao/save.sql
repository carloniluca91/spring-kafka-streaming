INSERT INTO _ingestion_alert
PARTITION(month)
VALUES (
:r.getDataSourceId,
:r.getLocalDateTime,
:r.getLocalDate,
:r.getExceptionClass,
:r.getExceptionMessage,
:r.getSentToKafkaTopic,
:r.getSavedIntoImpalaTable,
now(),
from_timestamp(now(), 'yyyy-MM-dd'),
from_timestamp(now(), 'yyyy-MM'))