package it.luca.spring.jdbc.core;

import it.luca.spring.enumeration.DataSourceId;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@UseClasspathSqlLocator
public interface GenericDao<T> {

    @SqlUpdate
    void createTable();

    DataSourceId getDataSourceId();

    default String getTableName() {
        return "t_rw_" + getDataSourceId().name().toLowerCase() + "_recover";
    }

    void save(T object);
}
