package it.luca.spring.jdbc.dao;

import it.luca.spring.jdbc.dto.IngestionRecord;

public interface IngestionRecordDao extends Dao, InsertSingleton<IngestionRecord> {}