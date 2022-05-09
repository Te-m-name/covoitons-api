package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.entity.RideEntity;
import com.example.covoitonsapi.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RideService implements IRideService{

    @Autowired
    private RideRepository repository;

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

        entity = repository.saveAndFlush(entity);

        return entity.getId();
    }
}
