# Spring Kafka Streaming

Spring Boot-based project for ingesting data on a Hadoop environment

The project consists of a Web Service that defines an API with one `POST` method 
for each datasource to be ingested. Input data are serialized in `json` format 
and written on some `Kafka` topics.

For every ingested message, a logging record is written in `Impala`