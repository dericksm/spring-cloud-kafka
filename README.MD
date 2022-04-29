# Spring Cloud Streams with Kafka

### I was messing up with Spring Cloud and tried to create a few 'microservices' to handle a few Kafka Streams using Spring Cloud Streams. 

## What I've used from Spring stack: 

[Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream) to build the messaging system;

[Spring Cloud Schema Registry](https://spring.io/projects/spring-cloud-schema-registry)  provides an easy way to store, retrieve and manage schemas and also has support for Apache Avro;

[Spring Data Elasticsearch](https://spring.io/projects/spring-data-elasticsearch) to store data on ElasticSearch.


## Requirements

For building and running the application you'll need:

- [JDK 11](https://openjdk.java.net/install/)
- [Maven 3](https://maven.apache.org)

## Modules overview

# stock-producer-api
Rest API which uses the AlphaVantage service to search stocks by its ticker and have a response based on daily data.  
Every time a stock is searched an event is pushed to the `com.github.dericksm.producer.stocks` Kafka topic.

# stock-processor-service
It listens to the `com.github.dericksm.producer.stocks` topic and process if the stock had one growth or loss, and then pushes it to `com.github.dericksm.producer.stocks` topic.

# stock-collector-service
Listens to the `com.github.dericksm.producer.stocks` topic and stores the data on ElasticSearch.

# stock-elastic-api
Rest API where you can search the data from the previous processed stock data.

## Environment

To run the project you'll need one Kafka, Zookeeper, Schema Registry and Elastic servers.
For this you can run the `docker-compose` file in the project root source.
