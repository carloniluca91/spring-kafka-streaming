package it.luca.spring.jdbc.core;

import com.cloudera.impala.jdbc.DataSource;
import it.luca.spring.jdbc.dao.IngestionAlertDao;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
        log.info("Initialized {}", jdbiClass);
    }

    /**
     * Insert record on Impala table using given dao class
     * @param object: record to be inserted
     * @param daoClass: dao class
     * @param <T>: record type parameter
     */

    public <T> void insertRecord(T object, Class<? extends GenericDao<T>> daoClass) {

        String rClassName = object.getClass().getSimpleName();
        String daoClassName = daoClass.getSimpleName();
        String tableName = jdbi.withHandle(handle -> handle.attach(daoClass).getTableName());
        if (jdbi.withHandle(handle -> handle.attach(UtilsDao.class).existTable(tableName))) {
            log.info("Table {} already exists", tableName);
        } else {
            log.warn("Table {} does not exist yet. Creating it now", tableName);
            jdbi.useHandle(handle -> handle.attach(daoClass).createTable());
            log.info("Successfully created table {}", tableName);
        }

        log.info("Saving instance of {} using {}", rClassName, daoClassName);
        jdbi.useHandle(handle -> handle.attach(daoClass).save(object));
        log.info("Saved instance of {} using {}", rClassName, daoClassName);
    }

    @Scheduled(cron = "00 50 23 * * ?")
    private void insertOverWriteIngestionAlert() {

        String tableName = jdbi.withHandle(handle -> handle.attach(IngestionAlertDao.class).getTableName());
        log.info("Issuing INSERT OVERWRITE on table {}", tableName);
        jdbi.useHandle(handle -> handle.attach(IngestionAlertDao.class).insertOverwrite());
        log.info("Successfully issued INSERT OVERWRITE on table {}", tableName);
    }
}
