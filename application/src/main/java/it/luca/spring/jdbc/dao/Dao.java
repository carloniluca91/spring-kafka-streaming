package it.luca.spring.jdbc.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@UseClasspathSqlLocator
public interface Dao<T> {

    @SqlUpdate
    void createTable();

    @SqlUpdate
    void insertOverwrite(@Bind("today") String today);

    @SqlUpdate
    void insertRecord(@BindMethods("r") T record);

}
