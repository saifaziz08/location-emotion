package io.template.zuulrekaconfig.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    public void save(MultipartFile picture, String path) {
        System.out.println("Saved File to path: " + path);
    }
}
