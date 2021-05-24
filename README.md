# Spring Kafka Streaming

Spring Boot application for receiving batches of data in `xml` or `json` format
from various sources and publish them on some `Kafka` topics

For each data source, a `post` method for receiving data is defined. 
Received data are deserialized into Java POJOs, wrapped within a standard container class 
and then serialized again as json to be published on Kafka. Each ingestion operation generates 
a row that is inserted on an `Impala` table (by means of `jdbi` framework) for logging purposes

The project consist on following modules

* `application` which contains Spring application
* `data-model` which contains data models and specifications for each source
