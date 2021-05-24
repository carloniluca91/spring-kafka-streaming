package it.luca.spring.jdbc.dao;

import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 * Dao interface for inserting a single bean of type T
 * @param <T>
 */

@UseClasspathSqlLocator
public interface InsertSingleton<T> {

    /**
     * Insert given bean
     * @param record bean to be inserted
     */

    @SqlUpdate
    void insertRecord(@BindMethods("r") T record);
}
