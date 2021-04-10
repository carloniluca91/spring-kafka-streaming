package it.luca.cedacri.jdbc.dao;

import it.luca.cedacri.jdbc.core.SaveDao;
import it.luca.cedacri.json.beans.Bancll34;
import it.luca.cedacri.json.core.MsgWrapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@UseClasspathSqlLocator
public interface Bancll34Dao extends SaveDao<Bancll34> {

    @Override
    @SqlUpdate
    void save(@BindBean MsgWrapper<Bancll34> msgWrapper);
}
