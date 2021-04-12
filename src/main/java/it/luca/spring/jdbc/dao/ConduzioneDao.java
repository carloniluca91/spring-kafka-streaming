package it.luca.spring.jdbc.dao;

import it.luca.spring.enumeration.DataSourceId;
import it.luca.spring.jdbc.bean.JDBCRecord;
import it.luca.spring.jdbc.core.GenericDao;
import it.luca.spring.json.conduzione.ConduzionePayload;
import it.luca.spring.json.conduzione.ConduzioneRecord;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlBatch;

import java.util.List;
import java.util.stream.Collectors;

public interface ConduzioneDao extends GenericDao<ConduzionePayload> {

    @Override
    default DataSourceId getDataSourceId() {
        return DataSourceId.CONDUZIONE;
    }

    @Override
    default void save(ConduzionePayload object) {

        List<JDBCRecord<ConduzioneRecord>> jdbcRecords = object.getRilevamenti()
                .stream().map(JDBCRecord::new)
                .collect(Collectors.toList());

        saveAll(jdbcRecords);
    }

    @SqlBatch
    void saveAll(@BindMethods("records") List<JDBCRecord<ConduzioneRecord>> conduzioneRecords);
}
