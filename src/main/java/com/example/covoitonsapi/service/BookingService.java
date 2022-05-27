package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.BookingDto;
import com.example.covoitonsapi.entity.RideEntity;
import com.example.covoitonsapi.entity.RidesUsersEntity;
import com.example.covoitonsapi.repository.BookingRepository;
import com.example.covoitonsapi.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingService implements IBookingService {

    @Autowired
    private BookingRepository repository;
    @Autowired
    private RideRepository rideRepository;

    @Override
    public Boolean reserved(BookingDto dto) {

        RidesUsersEntity entity = new RidesUsersEntity();
        //RideEntity rideEntity = rideRepository.findById(entity.getId_ride()).get();
        //rideEntity.setPlaces();

        entity.setAccepted(null);
        entity.setId_ride(dto.getRide_id());
        entity.setId_user(dto.getUser_id());

        repository.saveAndFlush(entity);

        return true;
    }

    @Override
    public Boolean canceled(BookingDto dto) {

        RidesUsersEntity entity = new RidesUsersEntity();
        entity.setId(dto.getId());

        repository.deleteById(entity.getId());
        return null;
    }
}
