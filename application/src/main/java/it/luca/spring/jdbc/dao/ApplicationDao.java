package it.luca.spring.jdbc.dao;

import it.luca.spring.jdbc.dto.IngestionRecord;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;

import static it.luca.utils.functional.Optional.isPresent;

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
    private void initJdbi() {

        String jdbiClass = Jdbi.class.getSimpleName();
        try {

            Class.forName(driverClass);
            Connection connection = DriverManager.getConnection(url);

            jdbi = Jdbi.create(connection)
                    .installPlugin(new SqlObjectPlugin())
                    .installPlugin(new PostgresPlugin());

            log.info("Successfully initialized {} instance", jdbiClass);
        } catch (Exception e) {
            log.error("Caught exception while initializing {} instance with driver '{}' and url {}. Class: {}. Message: {}",
                    jdbiClass, driverClass, url, e.getClass().getName(), e.getMessage());
        }
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
            if (isPresent(jdbi)) {
                jdbi.useHandle(handle -> handle.attach(IngestionRecordDao.class).insertRecord(record));
                log.info("Saved instance of {} using {}", recordClass, daoClassName);
            } else {
              log.warn("Current instance of {} will not be saved due to a previous error during {}'s initialization",
                      recordClass, Jdbi.class.getSimpleName());
            }
        } catch (Exception e) {
            log.error("Caught exception while saving instance of {}. Class: {}. Message: {}", recordClass, e.getClass().getName(), e.getMessage());
        }
    }
}
