## Location Emotion Backend

#### Prerequisites

+ [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) 
+ [Gradle](https://gradle.org/)
+ [Confluent](https://docs.confluent.io/platform/current/installation/installing_cp/zip-tar.html#get-the-software)

#### Run
+ Start each component in this order - waiting on the previous component to be completely started:
  + eureka component 

     `cd eureka/build/libs && java -jar eureka-0.0.1-SNAPSHOT.jar`

  + cloud-config component _(wait for eureka to start)_
     
     `cd cloud-config/build/libs && java -jar cloud-config-0.0.1-SNAPSHOT.jar`

  + zuul component _(wait for cloud-config to start)_
     
     `cd zuul/build/libs && java -jar zuul-0.0.1-SNAPSHOT.jar`

  + kafka-streams _(wait for cloud-config & zuul to start)_

     `cd kafka-streams/build/libs && java -jar kafka-streams-0.0.1-SNAPSHOT.jar`
  + 
  + kafka-consumer _(wait for kafka-streams)_

    `cd kafka-consumer/build/libs && java -jar kafka-consumer-0.0.1-SNAPSHOT.jar`

#### Verification
+ start all of the components
+ hit the zuul endpoint that should call through to the netflix-protected api, expect output that reads `hello world!`
  `curl http://localhost:8080/kafka-streams/hello`

#### Useful endpoints

 + __eureka registry__ --> http://localhost:8282
 
 + __kafka-streams with zuul__ --> http://localhost:8080/kafka-streams/hello
 
 + __zuul cloud-config__ --> http://localhost:9999/zuul/default
 
 + __kafka-streams cloud-config__ --> http://localhost:9999/kafka-streams/default
