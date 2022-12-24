package edu.school21.cinema.controlers;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.User;
import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.StorageService;
import edu.school21.cinema.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class UploadController {
    private final static Logger logger = LoggerFactory.getLogger(UploadController.class);

    @Autowired
    UserService userService;

    @Autowired
    FilmService filmService;

    @Autowired
    StorageService storageService;

    @RequestMapping("/uploadImage/{id}")
    public String uploadImage(@RequestParam("image_to_upload") MultipartFile multipartFile,
                              @PathVariable("id") Long id) throws Exception {
        logger.info("UploadImage...");
        if (multipartFile.isEmpty()) {
            throw new Exception("No user or File");
        }
        storageService.uploadImage(multipartFile, id);
        return "redirect:/admin/panel/films";
    }

    @RequestMapping("/uploadAvatar/{id}")
    public String uploadAvatar(@RequestParam("avatar_to_upload") MultipartFile multipartFile,
                              @PathVariable("id") Long id) throws Exception {
        logger.info("UploadAvatar...");
        if (multipartFile.isEmpty()) {
            throw new Exception("No user or File");
        }
        storageService.uploadAvatar(multipartFile, id);
        return "redirect:/profile";
    }

    @GetMapping("/avatars/{filename}")
    @ResponseBody
    public void getPoster(@PathVariable("filename") String filename,
                          HttpServletResponse response) throws IOException {
        logger.info("UploadAvatar...");
        storageService.load(null, filename, response);
    }

    @GetMapping("/avatars/{id}/{filename}")
    @ResponseBody
    public void getAvatar(@PathVariable("filename") String filename,
                          @PathVariable("id") Long id,
                          HttpServletResponse response) {
        logger.info("UploadAvatar...");
        storageService.load(id, filename, response);
    }
}
