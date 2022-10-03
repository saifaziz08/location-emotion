package org.example;

import org.example.models.Cluster;
import org.example.models.Metrics;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClusterService {
    public final Double threshold = 40.5;
    public Double calculateDistance(Metrics m1, Metrics m2) {
        double x1 = m1.getLatitude();
        double y1 = m1.getLongitude();
        double x2 = m2.getLatitude();
        double y2 = m2.getLongitude();
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public List<Cluster> calculateClusters(List<Metrics> metrics, String username){
        List<Cluster> results = new ArrayList<>();
        int len = metrics.size();
        Map<Metrics, List<Metrics>> neaghbours = new HashMap<>();

        for (int i = 0; i < len ; i++) {
            List<Metrics> closeProximity = new ArrayList<>();
            Metrics m1 = metrics.get(i);
            for (Metrics m2 : metrics) {
                Double distance = calculateDistance(m1, m2);
                if (distance <= threshold) {
                    closeProximity.add(m2);
                }
            }
            neaghbours.put(m1, closeProximity);
        }

        for (List<Metrics> m : neaghbours.values()) {

            OptionalDouble avgLat = m.stream().mapToDouble(Metrics::getLatitude).average();
            OptionalDouble avgLong = m.stream().mapToDouble(Metrics::getLongitude).average();
            List<Metrics> happyMetrics = m.stream().filter(x->"happy".equals(x.getMetricType())).collect(Collectors.toList());
            List<Metrics> sadMetrics = m.stream().filter(x->"sad".equals(x.getMetricType())).collect(Collectors.toList());
            List<Metrics> neutralMetrics = m.stream().filter(x->"neutral".equals(x.getMetricType())).collect(Collectors.toList());
            OptionalDouble avgHappy = happyMetrics.stream().mapToDouble(Metrics::getMetricValue).average();
            OptionalDouble avgSad = sadMetrics.stream().mapToDouble(Metrics::getMetricValue).average();
            OptionalDouble avgNeutral = neutralMetrics.stream().mapToDouble(Metrics::getMetricValue).average();

            if (avgLat.isPresent() && avgLong.isPresent() && avgSad.isPresent() && avgHappy.isPresent() && avgNeutral.isPresent()) {
                results.add(new Cluster
                                .ClusterBuilder()
                                .clusterName(UUID.randomUUID().toString())
                                .latitude(avgLat.getAsDouble())
                                .longitude(avgLong.getAsDouble())
                                .username(username)
                                .sad(avgSad.getAsDouble())
                                .happy(avgHappy.getAsDouble())
                                .neutral(avgNeutral.getAsDouble())
                                .build());
            }
        }

        return results;
    }

}
