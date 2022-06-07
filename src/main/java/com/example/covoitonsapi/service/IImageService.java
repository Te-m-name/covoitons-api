package com.example.covoitonsapi.service;

import com.example.covoitonsapi.entity.ImageEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface IImageService {
    ImageEntity uploadImageProfile(MultipartFile file) throws IOException;

    ImageEntity getImage() throws IOException;

    public Boolean ImgExist(Integer id);

    Integer updateImageProfile(MultipartFile file) throws IOException;
}
