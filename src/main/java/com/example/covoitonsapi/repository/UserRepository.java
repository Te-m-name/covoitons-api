package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
    Boolean existsByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.enabled = TRUE WHERE u.email = ?1")
    int enableUser(String email);

}
