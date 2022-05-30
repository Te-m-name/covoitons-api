package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.UserDto;

public interface IRegistrationService {
    String register(UserDto user) throws Exception;
    String confirmToken(String token);
}
