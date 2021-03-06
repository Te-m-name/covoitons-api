package com.example.covoitonsapi.service;

import com.example.covoitonsapi.entity.ConfirmationEntity;
import com.example.covoitonsapi.repository.ConfirmationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationService implements IConfirmationService {

    private final ConfirmationRepository confirmationRepository;

    @Override
    public void saveConfirmationToken(ConfirmationEntity token) {
        confirmationRepository.save(token);
    }

    public Optional<ConfirmationEntity> getToken(String token) {
        return confirmationRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return confirmationRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
