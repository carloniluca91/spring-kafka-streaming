INSERT INTO @impala.db.name@.@impala.table.ingestionError@ PARTITION (insert_dt)
VALUES (
:r.getMessageTs,
:r.getMessageDt,
:r.getDataSourceId,
:r.getDataSourceType,
:r.getInputDataClass,
:r.getAvroRecordClass,
:r.getTopicName,
:r.getExceptionClass,
:r.getExceptionMessage,
:r.getInsertTs,
:r.getInsertDt
)