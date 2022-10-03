package org.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EntityScan("org.example.models")
@EnableJpaRepositories("org.example.repository")
public class Main {

    public static void main(String[] args) throws IOException {
        Map<String, Boolean> tests = executeTests();
        for(Map.Entry<String, Boolean> test : tests.entrySet()){
            System.out.printf("[Test %s Result: %s]%n", test.getKey(), test.getValue());
        }
        System.exit(0);

    }
    public static Map<String, Boolean> executeTests() throws IOException {
        Map<String, Boolean> results = new HashMap<>();
        results.put("Basic Health Check for Kafka-Streams", heathCheckTest());
        results.put("Find Distributions for user", findDistribution());
        return results;
    }

    private static Boolean findDistribution() throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://localhost:8080/kafka-streams/distributions/user");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return !result.toString().equals("");
    }

    private static Boolean heathCheckTest() throws IOException {
        StringBuilder result = new StringBuilder();
        URL url = new URL("http://localhost:8080/kafka-streams/hello");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            for (String line; (line = reader.readLine()) != null; ) {
                result.append(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "hello world!".equals(result.toString());
    }

}