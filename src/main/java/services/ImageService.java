package services;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by daniel on 15/03/17.
 */
@Service
public class ImageService {

    public void saveImage(MultipartFile image) throws IOException {
        File file = new File("resources");
        image.transferTo(file);

    }

    public void store(MultipartFile file) throws Exception {
        try {
            Path rootLocation = Paths.get("/home/daniel/TEST");
            Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()));
        } catch (IOException e) {
            throw new Exception("Failed to store file " + file.getOriginalFilename(), e);
        }
    }

}