package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.entity.RideEntity;

public interface IRideService {

    Boolean exist(Integer id);

    RideDto toDto(RideEntity entity);

    RideDto getById(Integer id);
}
