# Spring Kafka Streaming

Spring Boot application for receiving batches of data in `.xml` or `.json` format
from various sources and publish them on some `Kafka` topics

For each data source, a `post` method for receiving data is defined. 
Received data are deserialized into `POJOs` and, if validated by some 
data quality checks, wrapped within a standard container class 
and then serialized again as json to be eventually published on `Kafka`. 

Each time data are received, the application produces a row stored in 
a `PostgreSQL` table (by means of `jdbi` framework) for logging purposes

The project consist on following modules

* `application` which contains Spring application
* `data-model` which contains data models and specifications for each source
