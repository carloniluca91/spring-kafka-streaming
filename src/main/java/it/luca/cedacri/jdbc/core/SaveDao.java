package it.luca.cedacri.jdbc.core;

import it.luca.cedacri.json.core.MsgBody;
import it.luca.cedacri.json.core.MsgWrapper;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@UseClasspathSqlLocator
public interface SaveDao<T extends MsgBody> {

    @SqlUpdate
    void save(@BindMethods MsgWrapper<T> msgWrapper);
}
