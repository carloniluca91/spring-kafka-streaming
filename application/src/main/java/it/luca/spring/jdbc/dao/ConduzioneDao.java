package it.luca.spring.jdbc.dao;

import it.luca.spring.jdbc.core.GenericDao;
import it.luca.spring.model.jdbc.JDBCRecord;
import it.luca.spring.model.json.conduzione.ConduzionePayload;
import it.luca.spring.model.json.conduzione.ConduzioneRecord;
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

        List<JDBCRecord<ConduzioneRecord>> jdbcRecords = object.getSamples()
                .stream().map(JDBCRecord::new)
                .collect(Collectors.toList());

        saveAll(jdbcRecords);
    }

    @SqlBatch
    void saveAll(@BindMethods("records") List<JDBCRecord<ConduzioneRecord>> conduzioneRecords);
}
