package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.entity.UserEntity;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUser();

    UserDto getCurrentUser();

    String add(UserEntity entity) throws Exception;

    int enableUser(String email);

}
