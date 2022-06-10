package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.entity.UserEntity;

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

    UserDto getUserById(Integer id);

    void refreshToken(HttpServletRequest request, HttpServletResponse response, String authorizationHeader) throws IOException;

}
