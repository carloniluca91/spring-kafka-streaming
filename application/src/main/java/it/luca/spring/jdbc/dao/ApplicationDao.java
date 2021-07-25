package it.luca.spring.jdbc.dao;

import it.luca.spring.jdbc.dto.IngestionRecord;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Slf4j
@Component
@EnableScheduling
public class ApplicationDao {

    @Autowired
    private DataSource dataSource;

    private Jdbi jdbi;

    @PostConstruct
    private void initJdbi() {

        jdbi = Jdbi.create(dataSource)
                .installPlugin(new SqlObjectPlugin())
                .installPlugin(new PostgresPlugin());

        log.info("Successfully initialized {} instance", jdbi.getClass().getSimpleName());
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
}
