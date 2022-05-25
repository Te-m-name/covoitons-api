package com.example.covoitonsapi.service;

import com.example.covoitonsapi.entity.ConfirmationEntity;
import com.example.covoitonsapi.repository.ConfirmationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ConfirmationService implements IConfirmationService {

    private final ConfirmationRepository confirmationRepository;

    @Override
    public void saveConfirmationToken(ConfirmationEntity token) {
        confirmationRepository.save(token);
    }
}
