package it.luca.spring.jdbc.dao;

import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

/**
 * Dao interface for creating table and
 * issuing insert overwrite statement on today's partition
 */

@UseClasspathSqlLocator
public interface Dao {

    @SqlUpdate
    void createTable();

    @SqlUpdate
    void insertOverwrite(@Bind("today") String today);

}
