# Spring Source Manager

Spring Boot-based project for ingesting data on a Hadoop environment

The project consists of a Web Service that defines an API with one `POST` method 
for each datasource to be ingested. Given request data are deserialized and written 
on some `Kafka` topics. In case Kafka brokers are not available, deserialized data
are stored directly on `HDFS` using simple text format 

Also, any anomaly detected during the process is stored on `Impala` for logging purposes