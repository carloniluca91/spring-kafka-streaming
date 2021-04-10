package it.luca.cedacri.jdbc.core;

import com.cloudera.impala.jdbc.DataSource;
import it.luca.cedacri.json.core.MsgBody;
import it.luca.cedacri.json.core.MsgWrapper;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class ImpalaDao {

    @Value("${spring.datasource.driverClassName}")
    private String driverClass;

    @Value("${spring.datasource.url}")
    private String url;

    private Jdbi jdbi;

    @PostConstruct
    private void dataSource() throws ClassNotFoundException {

        Class.forName(driverClass);
        DataSource dataSource = new DataSource();
        dataSource.setURL(url);

        String jdbiClass = Jdbi.class.getName();
        log.info("Initializing {}", jdbiClass);
        jdbi = Jdbi.create(dataSource).installPlugin(new SqlObjectPlugin());
        log.info("Initialized {}", jdbiClass);
    }

    public <T extends MsgBody> void save(MsgWrapper<T> object, Class<? extends SaveDao<T>> daoClass) {

        String rClassName = object.getClass().getSimpleName();
        String daoClassName = daoClass.getSimpleName();
        log.info("Saving instance of {} using {}", rClassName, daoClassName);
        jdbi.useHandle(handle -> handle.attach(daoClass).save(object));
        log.info("Saved instance of {} using {}", rClassName, daoClassName);
    }
}
