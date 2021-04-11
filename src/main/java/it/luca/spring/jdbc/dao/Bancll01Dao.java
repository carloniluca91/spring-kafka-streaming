package it.luca.spring.jdbc.dao;

import it.luca.spring.jdbc.bean.JDBCRecord;
import it.luca.spring.jdbc.core.GenericDao;
import it.luca.spring.json.bancll01.Bancll01Payload;
import it.luca.spring.json.bancll01.Bancll01Record;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindMethods;
import org.jdbi.v3.sqlobject.statement.SqlBatch;

import java.util.List;
import java.util.stream.Collectors;

public interface Bancll01Dao extends GenericDao<Bancll01Payload> {

    @Override
    default String getTableName() {
        return "bancll01";
    }

    @Override
    default void save(Bancll01Payload object) {

        List<JDBCRecord<Bancll01Record>> jdbcRecords = object.getNomine()
                .stream().map(JDBCRecord::new)
                .collect(Collectors.toList());

        saveAll(object.getDataOraInvio(), jdbcRecords);
    }

    @SqlBatch
    void saveAll(@Bind("dataOraInvio") String dataOraInvio,
                 @BindMethods("record") List<JDBCRecord<Bancll01Record>> bancll01Records);
}
