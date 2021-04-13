# Spring Source Manager

Spring Boot-based project for ingesting data on a Hadoop environment

The project consists of a Web Service that defines an API with one `POST` method 
for each datasource to be ingested. Given request data are serialized in `json` format 
and written on some `Kafka` topics. In case Kafka brokers are not available, 
serialized data are flattened and stored on `Impala` tables using `JDBI` framework

Also, any anomaly detected during the process is stored (again on Impala) for logging purposes