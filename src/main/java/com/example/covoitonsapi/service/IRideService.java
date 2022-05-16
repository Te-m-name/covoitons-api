package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.entity.RideEntity;

import java.util.List;

public interface IRideService {

    Boolean exist(Integer id);
    RideDto toDto(RideEntity entity);
    RideDto getById(Integer id);
    Integer add(RideDto dto);
    List<RideDto> getAllRides();
    List<RideDto> getRideByCity(String city);
    RideDto getNextRide();

}
