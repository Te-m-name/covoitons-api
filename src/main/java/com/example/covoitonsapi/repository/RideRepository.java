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

    @Query(value = "select TOP 1 * from rides_users join users on rides_users.id_passenger = users.id join rides on rides_users.id_ride = rides.id\n" +
            "where users.id = ?1 order by departure_time", nativeQuery = true)
    RideEntity nextRide(Integer id);
    @Query(value = "select * from rides_users join users on rides_users.id_passenger = users.id join rides on rides_users.id_ride = rides.id\n" +
            "where users.id = ?1 order by departure_time", nativeQuery = true)
    List<RideEntity> bookedRides(Integer id);
    @Query(value = "select distinct * from rides where rides.id_driver = ?1 order by departure_time", nativeQuery = true)
    List<RideEntity> proposedRides(Integer id);
}
