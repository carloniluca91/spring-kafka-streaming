package it.luca.cedacri.jdbc.dao;

import it.luca.cedacri.jdbc.core.DataSourceDao;
import it.luca.cedacri.json.bancll34.Bancll34;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface Bancll34Dao extends DataSourceDao<Bancll34> {

    @Override
    @SqlUpdate
    void save(@BindBean Bancll34 object);
}
