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

    @Query(value = "select * from rides where city = ?1 and home_to_office = ?2 and date BETWEEN ?3 and ?4", nativeQuery = true)
    List<RideEntity> findByCityAndHome_to_officeAndDeparture_time(String city, Boolean home_to_office, String date, String date2);
    List<RideEntity> findByCity(String city);

    @Query(value = "select TOP 1 * from rides_users join users on rides_users.id_user = users.id join rides on rides_users.id_ride = rides.id\n" +
            "where id_user = ?1 and departure_time > CURRENT_TIMESTAMP and accepted = 'true' order by departure_time", nativeQuery = true)
    RideEntity nextRide(Integer id);
    @Query(value = "select * from rides_users join users on rides_users.id_user = users.id join rides on rides_users.id_ride = rides.id\n" +
            "where id_user = ?1 and departure_time > CURRENT_TIMESTAMP and accepted = 'true' order by departure_time", nativeQuery = true)
    List<RideEntity> bookedRides(Integer id);
    @Query(value = "select distinct * from rides where rides.id_driver = ?1 and departure_time > CURRENT_TIMESTAMP order by departure_time", nativeQuery = true)
    List<RideEntity> proposedRides(Integer id);
    List<RideEntity> findTop4ByOrderByIdDesc();

    @Query(value = "select  * from rides where departure_time > CURRENT_TIMESTAMP order by departure_time", nativeQuery = true)
    List<RideEntity> findAllFuture();
}
