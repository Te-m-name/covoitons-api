package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.RecurrentRideDto;
import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.entity.DelaisEntity;
import com.example.covoitonsapi.entity.RecurrentRideEntity;
import com.example.covoitonsapi.entity.RideEntity;
import com.example.covoitonsapi.entity.UserEntity;
import com.example.covoitonsapi.repository.RecurrentRideRepository;
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

    @Autowired
    private RecurrentRideRepository recurrentRideRepository;

    @Override
    public Boolean exist(Integer id) { return rideRepository.existsById(id); }

    @Override
    public RideDto toDto(RideEntity entity) {

        RideDto dto = new RideDto();

        UserEntity user = entity.getUserEntity();

        dto.setDate(entity.getDeparture_time());
        dto.setArrival_time(entity.getArrivalTime());
        dto.setPlaces(entity.getPlaces());
        dto.setStreet(entity.getStreet());
        dto.setPost_code(entity.getPost_code());
        dto.setCity(entity.getCity());
        dto.setHome_to_office(entity.getHome_to_office());
        dto.setId_user(user.getID());
        dto.setId_ride(entity.getId());
        dto.setDriverFirstname(user.getFirstname());
        dto.setDriverLastname(user.getLastname());
        dto.setArrival_time(entity.getArrivalTime());
        dto.setLat(entity.getLat());
        dto.setLng(entity.getLng());

        if (entity.getHome_to_office()){
            dto.setDeparture(entity.getStreet() + " " + entity.getPost_code() + " " + entity.getCity());
            dto.setArrival("Ipipoe");

            return dto;
        } else {
            dto.setDeparture("Ipipoe");
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

        entity.setUserEntity(currentUser);
        entity.setCity(dto.getCity());
        entity.setStreet(dto.getStreet());
        entity.setPost_code(dto.getPost_code());
        entity.setDeparture_time(dto.getDate());
        entity.setArrivalTime(dto.getArrival_time());
        entity.setHome_to_office(dto.getHome_to_office());
        entity.setPlaces(dto.getPlaces());
        entity.setDate(dto.getDeparture_date());
        entity.setLat(dto.getLat());
        entity.setLng(dto.getLng());

        entity = rideRepository.saveAndFlush(entity);

        return entity.getId();
    }

    @Override
    public List<RideDto> getAllRides () {
        List<RideEntity> ridesList = rideRepository.findAllFuture();
        return ridesList.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }

    @Override
    public List<RideDto> getRideByCity(String city, Boolean home_to_office, String date, String date2) {
        List<RideEntity> ridesList = rideRepository.findByCityAndHome_to_officeAndDeparture_time(city, home_to_office, date, date2);
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
        List<RideEntity> lastRides = rideRepository.findTop4ByOrderByIdDesc();
        return lastRides.stream().map(e -> toDto(e)).collect(Collectors.toList());
    }

    public Integer addRecurrent(RecurrentRideDto dto){

        UserEntity currentUser = userRepository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        RecurrentRideEntity entity = new RecurrentRideEntity();
        DelaisEntity delaisEntity = new DelaisEntity();
        delaisEntity.setId(dto.getId_delais());

        entity.setHome_to_office(dto.getHome_to_office());
        entity.setDate(dto.getDate());
        entity.setArrivalTime(dto.getArrival_time());
        entity.setPlaces(dto.getPlaces());
        entity.setStreet(dto.getStreet());
        entity.setPost_code(dto.getPost_code());
        entity.setCity(dto.getCity());
        entity.setEnable(dto.getEnable());
        entity.setEnd_date(dto.getEnd_Date());
        entity.setDelais(delaisEntity);
        entity.setLat(dto.getLat());
        entity.setLng(dto.getLng());
        entity.setUserEntity(currentUser);

        entity = recurrentRideRepository.saveAndFlush(entity);

        return entity.getId();
    }

    public Date generateRecurrentRide(RecurrentRideEntity entity){
        RideEntity rideEntity = new RideEntity();
        
        rideEntity.setUserEntity(entity.getUserEntity());
        rideEntity.setCity(entity.getCity());
        rideEntity.setStreet(entity.getStreet());
        rideEntity.setPost_code(entity.getPost_code());
        rideEntity.setDeparture_time(entity.getDate());
        rideEntity.setArrivalTime(entity.getArrivalTime());
        rideEntity.setHome_to_office(entity.getHome_to_office());
        rideEntity.setPlaces(entity.getPlaces());
        rideEntity.setDate(entity.getDate());
        rideEntity.setLat(entity.getLat());
        rideEntity.setLng(entity.getLng());

        rideEntity = rideRepository.saveAndFlush(rideEntity);

        return rideEntity.getDeparture_time();
    }
}
