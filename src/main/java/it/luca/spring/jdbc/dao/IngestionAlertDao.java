package it.luca.spring.jdbc.dao;

import it.luca.spring.jdbc.bean.IngestionAlertRecord;
import it.luca.spring.jdbc.core.GenericDao;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface IngestionAlertDao extends GenericDao<IngestionAlertRecord> {

    @Override
    default String getTableName() {
        return "_ingestion_alert";
    }

    @Override
    @SqlUpdate
    void save(@BindMethods("r") IngestionAlertRecord object);
}
