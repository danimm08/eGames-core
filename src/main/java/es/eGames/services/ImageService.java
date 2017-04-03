package es.eGames.services;


import es.eGames.model.Image;
import es.eGames.model.PersonalGame;
import es.eGames.model.User;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;
import es.eGames.repositories.ImageRepository;
import es.eGames.security.services.UserDetailsService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 * Created by daniel on 15/03/17.
 */
@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private Environment env;

    @Autowired
    private UserService userService;

    @Autowired
    private PersonalGameService personalGameService;

    public ImageService() {
        super();
    }

    public void saveProfilePicture(MultipartFile image) throws Exception {
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
            Files.copy(image.getInputStream(), rootLocation.resolve(image.getOriginalFilename()));

            User principal = userService.findByUsername(username);
            principal.setProfilePicture(fullPath + "/" + image.getOriginalFilename());
            userService.update(principal);
        } catch (IOException e) {
            throw new Exception("Failed to saveProfilePicture file " + image.getOriginalFilename(), e);
        }
    }

    public void deletePicture(String fileName) throws Exception {
        User principal = userService.findByUsername(UserDetailsService.getPrincipal().getUsername());

        String rootPath = env.getProperty("es.eGames.images.rootPath");
        String fullFileName = rootPath + "/" + fileName;

        if(fileName.contains("personalGames")) {
            Image image = imageRepository.findByPathUrl(fileName);
            Assert.isTrue(image.getPersonalGame().getUser().equals(principal), "You are not authorized to perform this operation");
            imageRepository.delete(image.getId());
        }else{
            Assert.isTrue(principal.getProfilePicture().equals(fileName));
            principal.setProfilePicture(null);
            userService.save(principal);
        }
        File file = new File(fullFileName);
        file.delete();
    }


    public Resource loadAsResource(String filename) throws Exception {
        String rootPath = env.getProperty("es.eGames.images.rootPath");
        filename = rootPath + "/" + filename;
        Resource resource;
        Path file = Paths.get(filename);
        resource = new UrlResource(file.toUri());

        if (!(resource.exists() || resource.isReadable())) {
            throw new IllegalArgumentException("The file doesn't exist");
        }

        return resource;
    }

    public MediaType getContentType(String filename) {
        MediaType mediaType;
        String extension = filename.split("\\.")[1];

        switch (extension) {
            case "jpg":
                mediaType = MediaType.IMAGE_JPEG;
                break;
            case "jpeg":
                mediaType = MediaType.IMAGE_JPEG;
                break;
            case "png":
                mediaType = MediaType.IMAGE_PNG;
                break;
            default:
                mediaType = null;
                break;
        }
        return mediaType;
    }

    public void savePersonalGamePicture(MultipartFile image, String personalGameId) {
        try {
            Assert.notNull(personalGameId);
            PersonalGame personalGame = personalGameService.findById(personalGameId);

            String username = UserDetailsService.getPrincipal().getUsername();
            Assert.notNull(username, "You must be logged in");

            String rootPath = env.getProperty("es.eGames.images.rootPath");
            String personalGamePicturePath = "/" + username + env.getProperty("es.eGames.user.personalGamePicturePath") + "/" + personalGame.getGame().getTitle();
            String fullPath = rootPath + personalGamePicturePath;

            File directory = new File(fullPath);
            directory.mkdirs();

            String pathImageToSave = fullPath + "/" + image.getOriginalFilename();
            File imageToSave = new File(pathImageToSave);
            String filename;
            if (imageToSave.exists()) {
                String[] splittedFilename = image.getOriginalFilename().split("\\.");
                Random r = new Random();
                splittedFilename[0] += r.nextInt();

                filename = String.join(".", splittedFilename);
            } else {
                filename = image.getOriginalFilename();
            }
            pathImageToSave = fullPath + "/" + filename;

            System.out.println(filename);
            System.out.println(pathImageToSave);

            Path rootLocation = Paths.get(fullPath);
            Files.copy(image.getInputStream(), rootLocation.resolve(filename));

            Image pgImage = new Image();
            pgImage.setPathUrl(pathImageToSave);
            pgImage.setPersonalGame(personalGame);
            imageRepository.save(pgImage);

        } catch (IOException e) {
            try {
                throw new Exception("Failed to saveProfilePicture file " + image.getOriginalFilename(), e);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}