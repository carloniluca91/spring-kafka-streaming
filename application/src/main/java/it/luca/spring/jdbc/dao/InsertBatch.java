package it.luca.spring.jdbc.dao;

import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlBatch;

import java.util.List;

@UseClasspathSqlLocator
public interface InsertBatch<T> {

    @SqlBatch
    void insertRecords(@BindMethods("r") List<T> records);
}
