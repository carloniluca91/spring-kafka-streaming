package it.luca.spring.jdbc.dao;

import it.luca.spring.jdbc.core.GenericDao;
import it.luca.spring.json.bancll34.Bancll34Record;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface Bancll34Dao extends GenericDao<Bancll34Record> {

    @Override
    @SqlUpdate
    void save(@BindBean Bancll34Record object);
}
