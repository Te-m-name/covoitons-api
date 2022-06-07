package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
    Optional<ImageEntity> findByUserId(Integer name);
}
