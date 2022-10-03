package org.example.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class FacialAlgosService {
    String pathToPicture;
    public static final int ALGO_COUNT = 2;

    Random random = new Random();
    public Map<String, Map<String, Double>> findResultsFromAlgos(String pathToPicture) {
        this.pathToPicture = pathToPicture;
        Map<String, Map<String, Double>> results = new ConcurrentHashMap<>();
        results.put("algo-1", findMetricsRandomly());
        results.put("algo-2", findMetricsConstant());
        //results.put("algo-3 ") new way to calculate it
        return results;
    }
    private Map<String, Double> findMetricsRandomly() {
        //analyze the pic by an algorithm created internally or third party for the picture
        Map<String, Double> result = new HashMap<>();
        result.put("happy", random.nextDouble());
        result.put("sad", random.nextDouble());
        result.put("neutral", random.nextDouble());
        return result;
    }
    private Map<String, Double> findMetricsConstant() {
        Map<String, Double> result = new HashMap<>();
        result.put("happy", 0.33);
        result.put("sad", 0.33);
        result.put("neutral", 0.33);
        return result;
    }
}
