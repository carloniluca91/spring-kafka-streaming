INSERT INTO @impala.db.name@.@impala.table.ingestionSuccess@ PARTITION (insert_dt)
VALUES (
:r.getMessageTs,
:r.getMessageDt,
:r.getDataSourceId,
:r.getDataSourceType,
:r.getInputDataClass,
:r.getAvroRecordClass,
:r.getTopicName,
:r.getTopicPartition,
:r.getMessageOffset,
:r.getBatchTotalRecords,
:r.getBatchRecordProgressive,
:r.getInsertTs,
:r.getInsertDt
)