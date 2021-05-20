INSERT OVERWRITE @impala.db.name@.@impala.table.ingestionLog@
PARTITION (insert_dt)
SELECT *
FROM @impala.db.name@.@impala.table.ingestionLog@
WHERE insert_dt = :today;