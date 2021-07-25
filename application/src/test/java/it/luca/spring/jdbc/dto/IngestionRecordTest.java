package it.luca.spring.jdbc.dto;

import it.luca.spring.data.enumeration.DataSourceType;
import it.luca.spring.data.enumeration.IngestionOperationCode;
import it.luca.spring.data.model.common.SourceSpecification;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

class IngestionRecordTest {

    private static RecordMetadata recordMetadata;
    private static SourceSpecification<TestBean> sourceSpecification;

    private final static String EXPECTED_TOPIC = "test-topic";
    private final static int EXPECTED_PARTITION = 0;
    private final static long EXPECTED_OFFSET = 123;

    private final static Class<TestBean> EXPECTED_DATA_CLASS = TestBean.class;
    private final static String EXPECTED_DATASOURCE_ID = EXPECTED_DATA_CLASS.getSimpleName().toUpperCase();
    private final static DataSourceType EXPECTED_DATASOURCE_TYPE = DataSourceType.XML;

    @BeforeAll
    static void init() {

        recordMetadata = mock(RecordMetadata.class);

        doReturn(System.currentTimeMillis()).when(recordMetadata).timestamp();
        doReturn(EXPECTED_TOPIC).when(recordMetadata).topic();
        doReturn(EXPECTED_PARTITION).when(recordMetadata).partition();
        doReturn(EXPECTED_OFFSET).when(recordMetadata).offset();

        //noinspection unchecked
        sourceSpecification = (SourceSpecification<TestBean>) mock(SourceSpecification.class);
        doReturn(EXPECTED_DATASOURCE_ID).when(sourceSpecification).getDataSourceId();
        doReturn(EXPECTED_DATA_CLASS).when(sourceSpecification).getInputDataClass();
        doReturn(EXPECTED_DATASOURCE_TYPE).when(sourceSpecification).getDataSourceType();
    }

    @Test
    void initErrorRecord() {

        String EXCEPTION_MESSAGE = "exception-message";
        Exception exception = new IllegalArgumentException(EXCEPTION_MESSAGE);
        ErrorRecord record = new ErrorRecord(sourceSpecification, exception);

        assertNotNull(record.getMessageTs());
        assertNotNull(record.getMessageDt());
        assertEquals(EXPECTED_DATASOURCE_ID, record.getDataSourceId());
        assertEquals(EXPECTED_DATASOURCE_TYPE.name(), record.getDataSourceType());
        assertEquals(EXPECTED_DATA_CLASS.getName(), record.getInputDataClass());
        assertEquals(IngestionOperationCode.KO.name(), record.getIngestionOperationCode());
        assertNull(record.getTopicName());
        assertNull(record.getTopicPartition());
        assertNull(record.getMessageOffset());
        assertEquals(exception.getClass().getName(), record.getExceptionClass());
        assertEquals(exception.getMessage(), record.getExceptionMessage());
    }

    @Test
    void initSuccessRecord() {

        SuccessRecord record = new SuccessRecord(sourceSpecification, recordMetadata);

        assertNotNull(record.getMessageTs());
        assertNotNull(record.getMessageDt());
        assertEquals(EXPECTED_DATASOURCE_ID, record.getDataSourceId());
        assertEquals(EXPECTED_DATASOURCE_TYPE.name(), record.getDataSourceType());
        assertEquals(EXPECTED_DATA_CLASS.getName(), record.getInputDataClass());
        assertEquals(IngestionOperationCode.OK.name(), record.getIngestionOperationCode());
        assertEquals(EXPECTED_TOPIC, record.getTopicName());
        assertEquals(EXPECTED_PARTITION, record.getTopicPartition());
        assertEquals(EXPECTED_OFFSET, record.getMessageOffset());
        assertNull(record.getExceptionClass());
        assertNull(record.getExceptionMessage());
    }
}