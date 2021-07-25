package it.luca.spring.jdbc.dao;

import it.luca.spring.jdbc.dto.IngestionRecord;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

@UseClasspathSqlLocator
public interface IngestionRecordDao {

    /**
     * Insert given bean
     * @param record bean to be inserted
     */

    @SqlUpdate
    void insertRecord(@BindMethods("r") IngestionRecord record);
}