package it.luca.spring.jdbc.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@UseClasspathSqlLocator
public interface Dao {

    @SqlUpdate
    void createTable();

    @SqlUpdate
    void insertOverwrite(@Bind("today") String today);

}
