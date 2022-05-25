package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.ConfirmationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConfirmationRepository extends JpaRepository<ConfirmationEntity, Integer> {

    Optional<ConfirmationEntity> findByToken(String token);
}