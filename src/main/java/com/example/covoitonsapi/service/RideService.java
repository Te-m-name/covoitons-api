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

        dto.setDate(entity.getDeparture_time());
        dto.setPlaces(entity.getPlaces());
        dto.setStreet(entity.getStreet());
        dto.setPost_code(entity.getPost_code());
        dto.setCity(entity.getCity());
        dto.setHome_to_office(entity.getHome_to_office());
        dto.setId_user(entity.getId_user());

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

    @Override
    public Integer add(RideDto dto) {
        RideEntity entity = new RideEntity();

        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        entity.setPost_code(dto.getPost_code());
        entity.setDeparture_time(dto.getDate());
        entity.setHome_to_office(dto.getHome_to_office());
        entity.setPlaces(dto.getPlaces());
        entity.setId_user(dto.getId_user());

        entity = rideRepository.saveAndFlush(entity);

        return entity.getId();
    }
}
