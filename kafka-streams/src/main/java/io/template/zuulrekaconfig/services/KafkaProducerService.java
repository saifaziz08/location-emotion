package io.template.zuulrekaconfig.services;
import org.example.models.Picture;
import org.example.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducerService {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private PictureRepository pictureRepository;

    public void send(Picture pictureCapture, String topic) {

        String insertSql = "INSERT INTO Captured_Pictures(request_id, picture_path, longitude, latitude, user) VALUES (%s, %s, %f, %f, %s)%n";
        Picture savedPicture = pictureRepository.save(pictureCapture);
        ListenableFuture<SendResult<String, String>> future =
                kafkaTemplate.send(topic, savedPicture.getId().toString());

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.printf(insertSql,
                        pictureCapture.getRequestId(),
                        pictureCapture.getPicturePath(),
                        pictureCapture.getLongitude(),
                        pictureCapture.getLatitude(),
                        pictureCapture.getUsername());
                System.out.printf("Ingested [Picture %s, RequestId: %s, Id: %d]%n", pictureCapture.getPicturePath(), pictureCapture.getRequestId(), savedPicture.getId());
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Unable to deliver Message [" + pictureCapture + "] error " + ex.getMessage());
            }
        });
    }
}
