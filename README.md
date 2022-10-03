## Location Emotion Backend

#### Prerequisites

+ [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 
+ [Gradle](https://gradle.org/)
+ [Confluent](https://docs.confluent.io/platform/current/installation/installing_cp/zip-tar.html#get-the-software)

#### Run
+ Download Confluent and setup CONFLUENT_HOME environment variable, run kafka server by:
  `confluent local services kafka start`

+ Start each component in this order - waiting on the previous component to be completely started:
  + eureka component 

     `cd eureka/build/libs && java -jar eureka-0.0.1-SNAPSHOT.jar`

  + cloud-config component _(wait for eureka to start)_
     
     `cd cloud-config/build/libs && java -jar cloud-config-0.0.1-SNAPSHOT.jar`

  + zuul component _(wait for cloud-config to start)_
     
     `cd zuul/build/libs && java -jar zuul-0.0.1-SNAPSHOT.jar`

  + kafka-streams _(wait for cloud-config & zuul to start)_

     `cd kafka-streams/build/libs && java -jar kafka-streams-0.0.1-SNAPSHOT.jar`

  + kafka-consumer _(wait for kafka-streams)_

    `cd kafka-consumer/build/libs && java -jar kafka-consumer-0.0.1-SNAPSHOT.jar`
  
  + scheduled-service _(wait for kafka-consumer)_
  
  
  

    `cd scheduled-service/build/libs && java -jar scheduled-service-0.0.1-SNAPSHOT.jar`


#### Verification
+ start all of the components
+ hit the zuul endpoint that should call through to the netflix-protected api, expect output that reads `hello world!`
  `curl http://localhost:8080/kafka-streams/hello`
+ pictures send via post request are uploaded into the uploads folder

#### Useful endpoints

 + __eureka registry__ --> http://localhost:8282
 
 + __kafka-streams with zuul__ --> http://localhost:8080/kafka-streams/hello
 
 + __zuul cloud-config__ --> http://localhost:9999/zuul/default
 
 + __kafka-streams cloud-config__ --> http://localhost:9999/kafka-streams/default
 + __kafka-streams__ (send post request with picture attached to body as form-data) --> http://localhost:8080/kafka-streams/post?longitude=1.0&latitude=1.0&user=sa
 
 + __kafka-streams__ (send get request with username) 
 -> http://localhost:8080/kafka-streams/distributions/user
 
 + __kafka-streams__ (send get request with username) -->  http://localhost:8080/kafka-streams/picture/user



#### Microservices
+ Zuul
    `zuul is meant for load balancing, and request authorization`
+ Eureka
    `Eureka is meant for Service Discovery, it will show the status of all microservices`
+ Cloud-Config
    `Service to hold values that can be shared by microservices`
+ Kafka-Consumer
    `Service which will consume a picture and create metrics by running a facial expression recognition techniques`
+ Kafka-Service
    `General API service called for posting/getting distributions by user and picture`
+ Scheduled-Service
    `Service which runs in a regular time interval checking a queue to see if it needs to create clusters on new metrics`
+ Test-Service
    `Service for testing api`
 
