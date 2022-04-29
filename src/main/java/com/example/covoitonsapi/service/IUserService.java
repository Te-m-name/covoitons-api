package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.UserDto;

import java.util.List;

public interface IUserService {

    List<UserDto> getAllUser();

    Boolean add(UserDto dto);
}
