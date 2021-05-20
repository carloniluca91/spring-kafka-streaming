INSERT INTO @impala.db.name@.@impala.table.ingestionLog@ PARTITION (insert_dt)
VALUES (
:r.getMessageTs,
:r.getMessageDt,
:r.getDataSourceId,
:r.getDataSourceType,
:r.getInputDataClass,
:r.getTopicName,
:r.getTopicPartition,
:r.getIngestionOperationCode,
:r.getMessageOffset,
:r.getExceptionClass,
:r.getExceptionMessage,
:r.getInsertTs,
:r.getInsertDt
)