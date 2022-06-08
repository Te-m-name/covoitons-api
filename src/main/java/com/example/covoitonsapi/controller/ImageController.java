package com.example.covoitonsapi.controller;

import com.example.covoitonsapi.entity.ImageEntity;
import com.example.covoitonsapi.service.ImageService;
import com.example.covoitonsapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:4200", "https://covoitons-client-team-name4.vercel.app"})
@RestController
@RequestMapping("image")
@Slf4j
@AllArgsConstructor
public class ImageController {
    @Autowired
    private ImageService service;

    private UserService userService;

    @PostMapping("/upload")
    public ResponseEntity uploadImage(@RequestParam("imageFile") MultipartFile file) {
        Integer id = userService.getCurrentUser().getId();
        if (service.ImgExist(id)) {
            try {
                Integer img = service.updateImageProfile(file);
                return new ResponseEntity(null, HttpStatus.OK);
            } catch(Exception e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        try {
            service.uploadImageProfile(file);
            return new ResponseEntity(null, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get")
    public ResponseEntity getImage() {
        try {
            ImageEntity img = service.getImage();
            return new ResponseEntity(img, HttpStatus.OK);
        } catch(IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
