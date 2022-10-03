package io.template.zuulrekaconfig;

import io.template.zuulrekaconfig.services.FileStorageService;
import io.template.zuulrekaconfig.services.KafkaProducerService;
import org.example.models.Cluster;
import org.example.models.Picture;
import org.example.repository.ClusterRepository;
import org.example.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RefreshScope
@RestController
@CrossOrigin(origins = "*")
public class KafkaStreamsController {

    @Value("${external.property}")
    String property;

    @Value("${external.topic}")
    String topic;

    @Value("${external.pathToFile}")
    String pathToPictures;

    @Autowired
    ClusterRepository clusterRepository;

    @Autowired
    private KafkaProducerService kafkaProducerService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private PictureRepository pictureRepository;

    public KafkaStreamsController() { }

    @GetMapping("/hello")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok(property);
    }

    @GetMapping("/pictures/{user}")
    public ResponseEntity<List<Picture>> pictures(@PathVariable("user") String user) {
        return ResponseEntity.ok(pictureRepository.findByUser(user));
    }

    @GetMapping("/distributions/{user}")
    public ResponseEntity<Map<String, List<Cluster>>> distribution(@PathVariable("user") String user) {
        Map<String, List<Cluster>> distributions = new HashMap<>();
        distributions.put("Neutral", clusterRepository.getNeutralClusters(user));
        distributions.put("Happy", clusterRepository.getHappyClusters(user));
        distributions.put("Sad", clusterRepository.getSadClusters(user));
        return ResponseEntity.ok(distributions);
    }

    @PostMapping("/post")
    public ResponseEntity<Picture> ingest(@RequestBody MultipartFile picture,
                                 @RequestParam("longitude") Double longitude,
                                 @RequestParam("latitude") Double latitude,
                                 @RequestParam("user") String user) {

        String requestId = UUID.randomUUID().toString();
        String path = pathToPictures + requestId;
        fileStorageService.save(picture, path);
        Picture pictureCapture = new Picture
                .PictureCaptureBuilder()
                .requestId(requestId)
                .pathToPicture(path)
                .longitude(longitude)
                .latitude(latitude)
                .username(user)
                .build();

        kafkaProducerService.send(pictureCapture, topic);
        return ResponseEntity.ok(pictureCapture);
    }
}
