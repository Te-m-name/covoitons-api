package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.ConfirmationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationRepository extends JpaRepository<ConfirmationEntity, Integer> {

    Optional<ConfirmationEntity> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConfirmationEntity c SET c.confirmedAt = ?2 WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}