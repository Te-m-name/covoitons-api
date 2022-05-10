package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.RidesDto;
import com.example.covoitonsapi.entity.RidesEntity;

import java.util.List;

public interface IRidesService {

    List<RidesDto> getAllRides();

    RidesDto toDto(RidesEntity ride);
}
