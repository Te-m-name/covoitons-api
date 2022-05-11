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
}
