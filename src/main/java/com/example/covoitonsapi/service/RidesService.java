package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.RidesDto;
import com.example.covoitonsapi.entity.RidesEntity;
import com.example.covoitonsapi.repository.RidesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RidesService {

    @Autowired
    private RidesRepository repository;

    public List<RidesDto> getAllRides() {
        return null;
    }

    public RidesDto toDto(RidesEntity ride) {
        RidesDto rideDto = new RidesDto();
        rideDto.setDepartureTime(ride.getDepartureTime());
        rideDto.setPlaces(ride.getPlaces());

        if (ride.getHome_to_office())
            rideDto.setItinary(ride.getStreet() + ", " + ride.getCity() + " -> IPI POE" );
        else
            rideDto.setItinary("IPI POE -> " + ride.getStreet() + ", " + ride.getCity());

        return rideDto;
    }
}
