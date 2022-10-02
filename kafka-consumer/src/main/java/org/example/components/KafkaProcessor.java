package org.example.components;

import io.template.zuulrekaconfig.models.Metrics;
import io.template.zuulrekaconfig.models.Picture;
import io.template.zuulrekaconfig.models.RecalculationQueue;
import io.template.zuulrekaconfig.repository.PictureRepository;
import io.template.zuulrekaconfig.repository.RecalculationRepository;
import org.example.services.FacialAlgosService;
import org.example.services.PersistenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class KafkaProcessor {
    @Autowired
    PersistenceService persistenceService;
    @Autowired
    FacialAlgosService facialAlgosService;

    @Autowired
    RecalculationRepository recalculationRepository;

    @Autowired
    PictureRepository pictureRepository;


    @KafkaListener(topics="user", groupId = "kafkaconsumer")
    public void process(String picId) {
        String resultingMsg = "[%d Results from %s Algorithms ingested ]%n";
        Optional<Picture> pictureCapture = pictureRepository.findById(Integer.parseInt(picId));
        if (pictureCapture.isPresent()) {
            Map<String, Map<String, Double>> metrics = facialAlgosService.findResultsFromAlgos(pictureCapture.get().getPicturePath());
            List<Metrics> newMetrics = persistenceService.ingest(metrics, pictureCapture.get());
            newMetrics.forEach(System.out::println);
            System.out.printf(resultingMsg, newMetrics.size(), FacialAlgosService.ALGO_COUNT);
            recalculationRepository.save(new RecalculationQueue.RecalculationQueueBuilder().request(pictureCapture.get().getRequestId()).build());
        }
        else{
            System.out.println("Did not find picture with picture id: " + picId );
        }
    }
}
