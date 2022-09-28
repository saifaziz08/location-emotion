## Zuul Eureka and Cloud Configuration

#### Prerequisites

+ [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 
+ [Gradle](https://gradle.org/)
+ [Kafka] confluent-6.1.0 

#### Run
+ Start each component in this order - waiting on the previous component to be completely started:
  + Run Kafka server
  
      `confluent local kafka start`
      
  + eureka component 

     `cd eureka && ./gradlew clean bootrun`

  + cloud-config component _(wait for eureka to start)_
     
     `cd cloud-config && ./gradlew clean bootrun`

  + zuul component _(wait for cloud-config to start)_
     
     `cd zuul && ./gradlew clean bootrun`

  + kafka-streams _(wait for cloud-config to start)_

     `cd kafka-streams && ./gradlew clean bootrun`
  
  + kafka-consumers _(wait for cloud-config to start)_

     `cd kafka-consumers && ./gradlew clean bootrun`

#### Verification
+ start all of the components
+ hit the zuul endpoint that should call through to the netflix-protected api, expect output that reads `hello world!`
  `curl http://localhost:8080/kafka-streams/hello`

#### Useful endpoints

 + __eureka registry__ --> http://localhost:8282
 
 + __kafka-streams without zuul__ --> http://localhost:8181/hello
 
 + __kafka-streams with zuul__ --> http://localhost:8080/kafka-streams/hello
 
 + __zuul cloud-config__ --> http://localhost:9999/zuul/default
 
 Send a post request to http://localhost:8080/kafka-streams
 + with picture as part of the body and longitude/latitude as request parameters
 + output will be it processes on a picture and populates the storage with metrics about facial expressions using a variety of different algorithms.
 + for now as an example only randomly generated metrics are created.
