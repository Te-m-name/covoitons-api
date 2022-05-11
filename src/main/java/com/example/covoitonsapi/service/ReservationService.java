package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.ReservationDto;
import com.example.covoitonsapi.entity.RidesUsersEntity;
import com.example.covoitonsapi.repository.ReservationRideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService implements IReservationService{

    @Autowired
    private ReservationRideRepository repository;

    @Override
    public Boolean reserved(ReservationDto dto) {

        RidesUsersEntity entity = new RidesUsersEntity();
        entity.setAccepted(false);
        entity.setId_ride(dto.getRide_id());
        entity.setId_user(dto.getUser_id());

        repository.saveAndFlush(entity);

        return true;
    }
}
