package it.luca.spring.jdbc.core;

import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@UseClasspathSqlLocator
public interface GenericDao<T> {

    @SqlUpdate
    void createTable();

    String getTableName();

    void save(T object);
}
