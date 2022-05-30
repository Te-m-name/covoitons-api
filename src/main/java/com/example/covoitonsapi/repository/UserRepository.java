package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
    @Transactional
    @Modifying
    @Query("UPDATE UserEntity u SET u.is_admin=?1 WHERE u.ID=?2")
    void updateIsAdmin(Boolean is_admin, Integer id);

    /*
    @Modifying
    @Query(value = "UPDATE users SET is_admin= :is_admin WHERE id= :id", nativeQuery = true)
    Integer updateIsAdmin(@Param("is_admin") Byte is_admin,@Param("id")  Integer id);
 */
}
