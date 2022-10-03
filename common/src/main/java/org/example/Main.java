package org.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@SpringBootApplication
@EntityScan("org.example.models")
@EnableJpaRepositories("org.example.repository")
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}