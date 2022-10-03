package org.example;


import org.example.models.Cluster;
import org.example.models.Metrics;
import org.example.models.Picture;
import org.example.models.RecalculationQueue;
import org.example.repository.ClusterRepository;
import org.example.repository.MetricsRepository;
import org.example.repository.PictureRepository;
import org.example.repository.RecalculationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScheduledJobs {
    private static final Logger log = LoggerFactory.getLogger(ScheduledJobs.class);

    @Autowired
    RecalculationRepository recalculationRepository;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    ClusterService clusterService;

    @Autowired
    ClusterRepository clusterRepository;

    @Autowired
    MetricsRepository metricsRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(fixedRate = 10000)
    public void recalculate() {
        log.info("The time is {}", dateFormat.format(new Date()));
        Optional<RecalculationQueue> topQueuedEvent = recalculationRepository.findAll().stream().findFirst();
        if (topQueuedEvent.isPresent()){
            log.info("Found a top queued event {}", topQueuedEvent.get());
            List<Picture> pictures = pictureRepository.findByRequestId(topQueuedEvent.get().getRequestId());
            String username = pictures.get(0).getUsername();
            List<List<Metrics>> allMetrics = pictures.stream().map(x->metricsRepository.findByRequestId(x.getRequestId())).collect(Collectors.toList());
            List<Metrics> metrics = new ArrayList<>();
            for(List<Metrics> metric : allMetrics)
                metrics.addAll(metric);
            List<Cluster> clusters = clusterService.calculateClusters(metrics, username);
            metricsRepository.saveAll(metrics);
            clusterRepository.saveAll(clusters);
            recalculationRepository.deleteById(topQueuedEvent.get().getId());
        }
    }

}
