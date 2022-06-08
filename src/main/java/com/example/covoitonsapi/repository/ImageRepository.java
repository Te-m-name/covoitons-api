package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {
    Optional<ImageEntity> findByUserId(Integer name);

    @Transactional
    @Modifying
    @Query("UPDATE ImageEntity u SET u.name = ?1, u.type = ?2, u.picByte = ?3 WHERE u.userId = ?4")
    Integer updateImageProfile(String name, String type, byte[] picByte, Integer id);

    Boolean existsByUserId(Integer userId);
}
