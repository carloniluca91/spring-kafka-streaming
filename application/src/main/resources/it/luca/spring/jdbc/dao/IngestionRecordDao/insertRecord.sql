INSERT INTO @jdbc.log.table@ (
sample_generation_ts,
sample_generation_dt,
dataflow_id,
dataflow_type,
dataflow_class,
dataflow_url,
sample_generation_code,
exception_class,
exception_message
) VALUES (
:r.getSampleGenerationTs,
:r.getSampleGenerationDt,
:r.getDataflowId,
:r.getDataflowType,
:r.getDataflowClass,
:r.getDataflowUrl,
:r.getSampleGenerationCode,
:r.getExceptionClass,
:r.getExceptionMessage
);


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