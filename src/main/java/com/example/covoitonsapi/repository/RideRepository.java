package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.RideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface RideRepository extends JpaRepository<RideEntity, Integer> {

    @Query(value = "select * from rides where city = ?1 and home_to_office = ?2 and date = ?3", nativeQuery = true)
    List<RideEntity> findByCityAndHome_to_officeAndDeparture_time(String city, Boolean home_to_office, LocalDate date);

<<<<<<< HEAD
    List<RideEntity> findByCity(String city);

=======
    List<RideEntity> findTop5ByOrderByIdDesc();
>>>>>>> 9b9c8919a3557df7a77b631341f757797eb6a858
}
