package it.luca.spring.jdbc.dao;

import com.cloudera.impala.jdbc.DataSource;
import it.luca.spring.data.utils.DatePattern;
import it.luca.spring.jdbc.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.List;

import static it.luca.spring.data.utils.Utils.now;

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
        jdbi.useHandle(handle -> handle.attach(ErrorDtoDao.class).createTable());
        log.info("Initialized {} and created ingestion log table", jdbiClass);
    }

    public <T> void insertBatch(List<T> records, Class<InsertBatch<T>> insertBatchClass) {

        if (!records.isEmpty()) {

            String recordClass = records.get(0).getClass().getSimpleName();
            int recordsSize = records.size();
            String daoClass = insertBatchClass.getSimpleName();
            log.info("Saving {} instance(s) of {} using {}", recordsSize, recordClass, daoClass);
            jdbi.useHandle(handle -> handle.attach(insertBatchClass).insertRecords(records));
            log.info("Saved {} instance(s) of {} using {}", recordsSize, recordClass, daoClass);
        }
    }

    public void insertRecord(ErrorDto record) {

        String recordClass = record.getClass().getSimpleName();
        log.info("Saving instance of {}", recordClass);
        jdbi.useHandle(handle -> handle.attach(ErrorDtoDao.class).insertRecord(record));
        log.info("Saved instance of {}", recordClass);
    }

    @Scheduled(cron = "30 59 23 * * *")
    private void insertOverWrite() {

        String today = now(DatePattern.DEFAULT_DATE);
        log.info("Issuing INSERT OVERWRITE on ingestion log table (partition = {})", today);
        jdbi.useHandle(handle -> handle.attach(ErrorDtoDao.class).insertOverwrite(today));
        log.info("Successfully issued INSERT OVERWRITE on ingestion log table (partition = {})", today);
    }
}
