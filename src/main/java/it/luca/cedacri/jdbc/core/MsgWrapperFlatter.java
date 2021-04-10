package it.luca.cedacri.jdbc.core;

import it.luca.cedacri.json.core.MsgWrapper;

import java.util.List;

public interface MsgWrapperFlatter<T, R extends JDBCRecord> {

    List<R> map(MsgWrapper<T> msgWrapper);
}
