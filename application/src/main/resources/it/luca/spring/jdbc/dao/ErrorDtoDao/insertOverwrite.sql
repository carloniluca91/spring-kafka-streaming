INSERT OVERWRITE @impala.db.name@.@impala.table.ingestionError@
PARTITION (insert_dt)
SELECT *
FROM @impala.db.name@.@impala.table.ingestionError@
WHERE insert_dt = :today;