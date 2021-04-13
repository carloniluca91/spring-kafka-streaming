package it.luca.spring.jdbc.dao;

import it.luca.spring.model.jdbc.IngestionAlertRecord;
import it.luca.spring.jdbc.core.GenericDao;
import it.luca.spring.utils.DataSourceId;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface IngestionAlertDao extends GenericDao<IngestionAlertRecord> {

    @Override
    default DataSourceId getDataSourceId() {
        return null;
    }

    @Override
    default String getTableName() {
        return "ingestion_alert_log";
    }

    @SqlUpdate
    void insertOverwrite();

    @Override
    @SqlUpdate
    void save(@BindMethods("r") IngestionAlertRecord object);
}
