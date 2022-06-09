package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.entity.ImageEntity;
import com.example.covoitonsapi.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageService implements IImageService {
    @Autowired
    UserService userService;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public ImageEntity uploadImageProfile(MultipartFile file) throws IOException {
        UserDto currentUser = userService.getCurrentUser();

        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        ImageEntity img = new ImageEntity(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()), currentUser.getId());
        imageRepository.save(img);
        return img;
    }

    @Override
    public ImageEntity getImage() throws IOException {
        UserDto currentUser = userService.getCurrentUser();

        final Optional<ImageEntity> retrievedImage = imageRepository.findByUserId(currentUser.getId());
        ImageEntity img = new ImageEntity(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()), retrievedImage.get().getUserId());
        return img;
    }

    @Override
    public Boolean ImgExist(Integer id) {
        return imageRepository.existsByUserId(id);
    }

    @Override
    public Integer updateImageProfile(MultipartFile file) throws IOException {
        UserDto currentUser = userService.getCurrentUser();

        return imageRepository.updateImageProfile(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()), currentUser.getId());
    }

    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }

        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
