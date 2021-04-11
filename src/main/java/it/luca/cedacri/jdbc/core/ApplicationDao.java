package it.luca.cedacri.jdbc.core;

import com.cloudera.impala.jdbc.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
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

    public <T> void saveUnsentMessage(T object, Class<? extends DataSourceDao<T>> daoClass) {

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
}
