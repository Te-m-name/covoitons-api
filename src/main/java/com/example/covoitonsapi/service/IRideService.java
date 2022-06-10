package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.entity.RideEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public interface IRideService {

    Boolean exist(Integer id);
    RideDto toDto(RideEntity entity);
    RideDto getById(Integer id);
    Integer add(RideDto dto);
    List<RideDto> getAllRides();
    List<RideDto> getBookedRides(Integer id);
    List<RideDto> getProposedRides(Integer id);RideDto getNextRide(Integer id);

    List<RideDto> getRideByCity(String city, Boolean home_to_office, String date, String date2);
    List<RideDto> getLast5Rides();

}
