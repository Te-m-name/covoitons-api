package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.entity.RideEntity;
import com.example.covoitonsapi.entity.UserEntity;
import com.example.covoitonsapi.repository.RideRepository;
import com.example.covoitonsapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RideService implements IRideService {

    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private UserRepository userRepository;

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
        dto.setId_ride(entity.getId());

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
        UserEntity currentUser = userRepository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        RideEntity entity = new RideEntity();

        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        entity.setPost_code(dto.getPost_code());
        entity.setDeparture_time(dto.getDate());
        entity.setHome_to_office(dto.getHome_to_office());
        entity.setPlaces(dto.getPlaces());
        entity.setId_user(currentUser.getID());
        entity.setDate(dto.getDeparture_date());

        entity = rideRepository.saveAndFlush(entity);

        return entity.getId();
    }

    @Override
    public List<RideDto> getAllRides () {
        List<RideEntity> ridesList = rideRepository.findAll();

        return ridesList.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }

    @Override
    public List<RideDto> getRideByCity(String city, Boolean home_to_office, LocalDate date) {
        List<RideEntity> ridesList = rideRepository.findByCityAndHome_to_officeAndDeparture_time(city, home_to_office, date);
        return ridesList.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }

    @Override
    public List<RideDto> getBookedRides(Integer id) {
        List<RideEntity> bookedRides = rideRepository.bookedRides(id);
        return bookedRides.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }

    @Override
    public List<RideDto> getProposedRides(Integer id) {
        List<RideEntity> proposedRides = rideRepository.proposedRides(id);
        return proposedRides.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }

    public RideDto getNextRide(Integer id) {
        RideEntity nextRide = rideRepository.nextRide(id);
        return toDto(nextRide);
    }
    public List<RideDto> getLast5Rides() {
        List<RideEntity> lastRides = rideRepository.findTop5ByOrderByIdDesc();
        return lastRides.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }
}
