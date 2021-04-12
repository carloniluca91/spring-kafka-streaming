package it.luca.spring.jdbc.dao;

import it.luca.spring.enumeration.DataSourceId;
import it.luca.spring.jdbc.bean.JDBCRecord;
import it.luca.spring.jdbc.core.GenericDao;
import it.luca.spring.json.jarvis.JarvisPayload;
import it.luca.spring.json.jarvis.JarvisRecord;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.stream.Collectors;

public interface JarvisDao extends GenericDao<JarvisPayload> {

    @Override
    default DataSourceId getDataSourceId() {
        return DataSourceId.JARVIS;
    }

    @Override
    default void save(JarvisPayload object) {

        List<JDBCRecord<JarvisRecord>> jdbcRecords = object.getListaCicli()
                .stream().map(JDBCRecord::new)
                .collect(Collectors.toList());

        saveAll(object.getAmbitoFlusso(), object.getNomeFlusso(), object.getImpresaMittente(), object.getDataDiCreazione(),
                object.getNumeroDati(), object.getDataProcedura(), object.getGiornoGas(), jdbcRecords);

    }

    @SqlUpdate
    void saveAll(@Bind String ambitoFlusso,
                 @Bind String nomeFlusso,
                 @Bind String impresaMittente,
                 @Bind String dataDiCreazione,
                 @Bind Integer numeroDati,
                 @Bind String dataProcedura,
                 @Bind String giornoGas,
                 @BindMethods("records") List<JDBCRecord<JarvisRecord>> jdbcRecords);
}
