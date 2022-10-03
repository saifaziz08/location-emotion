package org.example.components;

import org.example.models.Metrics;
import org.example.models.Picture;
import org.example.models.RecalculationQueue;
import org.example.repository.PictureRepository;
import org.example.repository.RecalculationRepository;
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
