package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.RideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<RideEntity, Integer> {

    @Query(value = "select * from rides where city = ?1", nativeQuery = true)
    List<RideEntity> getRidesByCity(String city);

    List<RideEntity> findByCity(String city);

    @Query(value = "    select limit 1 " +
            "           from rides join rides_users on rides.id = rides_users.id_ride " +
            "                       join users on ")
}
