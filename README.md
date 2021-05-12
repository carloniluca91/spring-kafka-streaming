# Spring Kafka Streaming

Spring Boot application for receiving batches of data in `xml` or `json` format
from various sources, convert them as `.avro` records and publish them on some 
`Kafka` topics

The project consist on following modules

* `application` which contains Spring application
* `data-model` which contains data models for each source
