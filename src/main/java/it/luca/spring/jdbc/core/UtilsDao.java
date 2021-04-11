package it.luca.spring.jdbc.core;

import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@UseClasspathSqlLocator
public interface UtilsDao {

    default Boolean existTable(String tableName) {
        return showTables().stream()
                .anyMatch(s -> s.equalsIgnoreCase(tableName));
    }

    @SqlQuery
    List<String> showTables();
}
