INSERT OVERWRITE @impala.db.name@.@impala.table.ingestionSuccess@
PARTITION (insert_dt)
SELECT *
FROM @impala.db.name@.@impala.table.ingestionSuccess@
WHERE insert_dt = :today;