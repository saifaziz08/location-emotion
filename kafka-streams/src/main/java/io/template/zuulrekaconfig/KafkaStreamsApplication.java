package io.template.zuulrekaconfig;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import java.sql.SQLException;

@EnableEurekaClient
@SpringBootApplication
@EntityScan("org.example.models")
@EnableJpaRepositories("org.example.repository")
public class KafkaStreamsApplication {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public static void main(String[] args) {
    SpringApplication.run(KafkaStreamsApplication.class, args);
  }

  @Bean(initMethod = "start", destroyMethod = "stop")
  public Server inMemoryH2DatabaseServer() throws SQLException {
    return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9091");
  }
}