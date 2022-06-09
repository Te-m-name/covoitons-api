package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.RecurrentRideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecurrentRideRepository extends JpaRepository<RecurrentRideEntity, Integer> {

    @Query(value = "SELECT * FROM recurrents_rides WHERE enable= 'true'", nativeQuery = true)
    List<RecurrentRideEntity> selectRecurrentsRides();
}
