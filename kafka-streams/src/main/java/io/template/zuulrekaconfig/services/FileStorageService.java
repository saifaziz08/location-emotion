package io.template.zuulrekaconfig.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

@Service
public class FileStorageService {
    private final Path root = Paths.get("uploads");
    public void save(MultipartFile picture, String path) {
        try {
            Files.copy(picture.getInputStream(), this.root.resolve(Objects.requireNonNull(picture.getOriginalFilename())));
            System.out.println("Saved File to path: " + path);}
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
