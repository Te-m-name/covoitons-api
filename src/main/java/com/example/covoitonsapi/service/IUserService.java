package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.entity.ImageEntity;
import com.example.covoitonsapi.entity.UserEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface IUserService {

    List<UserDto> getAllUser();

    UserDto getCurrentUser();

    String add(UserEntity entity) throws Exception;

    int enableUser(String email);

    UserEntity getUser(String email);

    void refreshToken(HttpServletRequest request, HttpServletResponse response, String authorizationHeader) throws IOException;

    ImageEntity uploadImageProfile(MultipartFile file) throws IOException;

    ImageEntity getImage(Integer userId) throws IOException;

}
