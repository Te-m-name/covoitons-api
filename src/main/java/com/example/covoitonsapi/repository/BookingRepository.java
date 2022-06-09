package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.RideEntity;
import com.example.covoitonsapi.entity.RidesUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<RidesUsersEntity, Integer> {


    @Query(value = "select * from rides_users join rides on rides_users.id_ride = rides.id where rides.id_driver = ?1 and rides_users.accepted is null order by departure_time", nativeQuery = true)
    List<RidesUsersEntity> BookingOnMyRide(Integer id);


    @Query(value = "select * from rides_users join rides on rides_users.id_ride = rides.id where rides_users.id_user = ?1 and rides_users.accepted is null order by departure_time", nativeQuery = true)
    List<RidesUsersEntity> MyBookingRequest(Integer id);

}
