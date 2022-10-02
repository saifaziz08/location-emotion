package io.template.zuulrekaconfig.services;

import io.template.zuulrekaconfig.models.Cluster;
import io.template.zuulrekaconfig.models.Metrics;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClusterService {
    public List<Cluster> calculateClusters(List<Metrics> metrics){
        List<Cluster> results = new ArrayList<>();
        return results;
    }

}
