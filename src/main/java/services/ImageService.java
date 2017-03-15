package services;


import model.User;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import security.UserDetailsService;

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

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    public ImageService() {
        super();
    }

    public void saveProfilePicture(MultipartFile file) throws Exception {
        try {
            String rootPath = env.getProperty("es.eGames.images.rootPath");
            String username = UserDetailsService.getPrincipal().getUsername();
            Assert.notNull(username, "You must be logged in");
            String profilePicturePath = "/" + username + env.getProperty("es.eGames.user.profilePicturePath");
            String fullPath = rootPath + profilePicturePath;

            File directory = new File(fullPath);
            FileUtils.deleteDirectory(directory);
            directory.mkdirs();

            Path rootLocation = Paths.get(fullPath);
            Files.copy(file.getInputStream(), rootLocation.resolve(file.getOriginalFilename()));

            User principal = userService.findByUsername(username);
            principal.setProfilePicture(fullPath + "/" + file.getOriginalFilename());
            userService.update(principal);
        } catch (IOException e) {
            throw new Exception("Failed to saveProfilePicture file " + file.getOriginalFilename(), e);
        }
    }

}