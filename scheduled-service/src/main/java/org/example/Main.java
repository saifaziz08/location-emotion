package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableEurekaClient
@SpringBootApplication
@EntityScan("org.example.models")
@EnableJpaRepositories("org.example.repository")
public class Main {

    public static void main(String[] args) {
        System.setProperty("spring.datasource.url","jdbc:h2:tcp://localhost:9091/mem:appdb");
        SpringApplication.run(Main.class, args);
    }
}