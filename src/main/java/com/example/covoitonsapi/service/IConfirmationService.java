package com.example.covoitonsapi.service;


import com.example.covoitonsapi.entity.ConfirmationEntity;

public interface IConfirmationService {
    void saveConfirmationToken(ConfirmationEntity token);
}
