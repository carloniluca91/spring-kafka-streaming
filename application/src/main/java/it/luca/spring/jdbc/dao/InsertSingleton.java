package it.luca.spring.jdbc.dao;

import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@UseClasspathSqlLocator
public interface InsertSingleton<T> {

    @SqlUpdate
    void insertRecord(@BindMethods("r") T record);
}
