package it.luca.spring.jdbc.dao;

import com.cloudera.impala.jdbc.DataSource;
import it.luca.spring.data.utils.DatePattern;
import it.luca.spring.jdbc.dto.IngestionRecord;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static it.luca.utils.time.Supplier.now;

@Slf4j
@Component
@EnableScheduling
public class ApplicationDao {

    @Value("${spring.datasource.driverClassName}")
    private String driverClass;

    @Value("${spring.datasource.url}")
    private String url;

    private Jdbi jdbi;

    @PostConstruct
    private void initJdbi() throws ClassNotFoundException {

        Class.forName(driverClass);
        DataSource dataSource = new DataSource();
        dataSource.setURL(url);

        String jdbiClass = Jdbi.class.getName();
        log.info("Initializing {}", jdbiClass);
        jdbi = Jdbi.create(dataSource).installPlugin(new SqlObjectPlugin());
        jdbi.useHandle(handle -> handle.attach(IngestionRecordDao.class).createTable());
        log.info("Initialized {} and created ingestion log table", jdbiClass);
    }

    /**
     * Insert ingestionRecord
     * @param record record to be inserted
     * @param <T> type of record to be inserted
     */

    public <T extends IngestionRecord> void insertIngestionRecord(T record) {

        String recordClass = record.getClass().getSimpleName();
        String daoClassName = IngestionRecordDao.class.getSimpleName();
        log.info("Saving instance of {} using {}", recordClass, daoClassName);
        try {
            jdbi.useHandle(handle -> handle.attach(IngestionRecordDao.class).insertRecord(record));
            log.info("Saved instance of {} using {}", recordClass, daoClassName);
        } catch (Exception e) {
            log.error("Caught exception while saving instance of {}. Class: {}. Message: {}", recordClass, e.getClass().getName(), e.getMessage());
        }
    }

    /**
     * Reduces the number of .parquet files on today's partition of log table
     */

    @Scheduled(cron = "55 59 23 * * *")
    private void insertOverWrite() {

        String today = now(DatePattern.DEFAULT_DATE);
        try {
            Thread.sleep(10000);
            log.info("Issuing INSERT OVERWRITE on ingestion log table (partition = {})", today);
            jdbi.useHandle(handle -> handle.attach(IngestionRecordDao.class).insertOverwrite(today));
            log.info("Successfully issued INSERT OVERWRITE on ingestion log table (partition = {})", today);
        } catch (Exception e) {
            log.error("Caught exception while issuing INSERT OVERWRITE on ingestion log table (partition = {}). Class: {}. Message: {}",
                    today, e.getClass().getName(), e.getMessage());
        }
    }
}
