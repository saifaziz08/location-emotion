package org.example.services;
import org.example.models.Metrics;
import org.example.models.Picture;
import org.example.repository.MetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PersistenceService {
    @Autowired
    MetricsRepository metricsRepository;
    public List<Metrics> ingest(Map<String, Map<String, Double>> metrics, Picture pictureCapture) {
        List<Metrics> results = new ArrayList<>();
        for(Map.Entry<String, Map<String, Double>> entry : metrics.entrySet()){
            String label = entry.getKey();
            Map<String, Double> algoMetrics = entry.getValue();
            results.addAll(algoMetrics
                    .entrySet()
                    .stream()
                    .map(x -> (new Metrics.MetricsBuilder()
                            .longitude(pictureCapture.getLongitude())
                            .latitude(pictureCapture.getLatitude())
                            .algorithm(label)
                            .request(pictureCapture.getRequestId())
                            .metricType(x.getKey())
                            .metricValue(x.getValue())
                            .build()))
                    .collect(Collectors.toList()));
        }
        return metricsRepository.saveAll(results);
    }
}
