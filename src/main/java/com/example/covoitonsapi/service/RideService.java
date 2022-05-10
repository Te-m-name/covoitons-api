package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.entity.RideEntity;
import com.example.covoitonsapi.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideService implements IRideService {

    @Autowired
    private RideRepository rideRepository;

    @Override
    public Boolean exist(Integer id) {
        return rideRepository.existsById(id);
    }

    @Override
    public RideDto toDto(RideEntity entity) {

        RideDto dto = new RideDto();

        dto.setDeparture_time(entity.getDeparture_time());
        dto.setPlaces(entity.getPlaces());

        if (entity.getHome_to_office()){
            dto.setDeparture(entity.getStreet() + " " + entity.getPost_code() + " " + entity.getCity());
            dto.setArrival("IPIPOE");

            return dto;
        } else {
            dto.setDeparture("IPIPOE");
            dto.setArrival(entity.getStreet() + " " + entity.getPost_code() + " " + entity.getCity());
            return dto;
        }
    }

    @Override
    public RideDto getById(Integer id) {
        return toDto(rideRepository.findById(id).get());
    }
}
