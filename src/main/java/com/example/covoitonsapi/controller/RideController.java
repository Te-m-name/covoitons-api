package com.example.covoitonsapi.controller;

import com.example.covoitonsapi.dto.RecurrentRideDto;
import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.entity.RideEntity;
import com.example.covoitonsapi.service.RideService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = {"http://localhost:4200", "https://covoitons-client-team-name4.vercel.app"})
@RestController
@RequestMapping("ride")
@Slf4j
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping("getARide/{id}")
    public ResponseEntity<RideDto> getARide(@PathVariable String id) {

        // convertir l'id en Integer
        Integer ID = Integer.parseInt(id);

        // verifier si le tajet existe ou non
        if (!rideService.exist(ID))
            return new ResponseEntity("le trajet n'existe pas", HttpStatus.NOT_FOUND);

        // on recupere le trajet transformÃ© en amont en DTO
        RideDto dto = rideService.getById(ID);

        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("getNextRide/{id}")
    public ResponseEntity<RideDto> getNextRide(@PathVariable String id) {

        Integer ID = Integer.parseInt(id);

        try {
            RideDto dto = rideService.getNextRide(ID);
            return new ResponseEntity(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("add")
    public ResponseEntity<Integer> add(@RequestBody RideDto dto){
        Date today = new Date();
        int test_date = dto.getDate().compareTo(today);

        if(test_date<0){
            return new ResponseEntity("Date incorrecte", HttpStatus.BAD_REQUEST);
        }

        if(dto.getPlaces()<1){
            return new ResponseEntity("Nombre de place incorrect", HttpStatus.BAD_REQUEST);
        }

        try{
            Integer id = rideService.add(dto);
            return  new ResponseEntity(id, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getAll")
    public ResponseEntity<List<RideDto>> getAll() {
        try {
            return new ResponseEntity(rideService.getAllRides(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("bookedRides/{id}")
    public ResponseEntity<List<RideDto>> getBookedRides(@PathVariable Integer id) {
        try {
            return new ResponseEntity(rideService.getBookedRides(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("proposedRides/{id}")
    public ResponseEntity<List<RideDto>> getProposedRides(@PathVariable Integer id) {
        try {
            return new ResponseEntity(rideService.getProposedRides(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("get")
    public ResponseEntity<List<RideDto>> searchByCity(@RequestParam String city, @RequestParam Boolean home_to_office, @RequestParam String date) {
        try {
            String toDate = date + " 00:00:00.0000000";
            String date2 = date + " 23:59:0.0000000";
            return new ResponseEntity(rideService.getRideByCity(city, home_to_office, toDate, date2), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getLast")
    public ResponseEntity<List<RideDto>> getLast() {
        try {
            return new ResponseEntity(rideService.getLast5Rides(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("addRecurrentRide")
    public ResponseEntity<Integer> addRecurrentRide(@RequestBody RecurrentRideDto dto){

        Date today = new Date();
        int test_date = dto.getDate().compareTo(today);

        if(test_date<0){
            return new ResponseEntity("Date incorrecte", HttpStatus.BAD_REQUEST);
        }

        if(dto.getPlaces()<1){
            return new ResponseEntity("Nombre de place incorrect", HttpStatus.BAD_REQUEST);
        }

        RideDto rideDto = new RideDto();

        rideDto.setCity(dto.getCity());
        rideDto.setStreet(dto.getStreet());
        rideDto.setPost_code(dto.getPost_code());
        rideDto.setDate(dto.getDate());
        rideDto.setArrival_time(dto.getArrival_time());
        rideDto.setHome_to_office(dto.getHome_to_office());
        rideDto.setPlaces(dto.getPlaces());
        rideDto.setDeparture_date(dto.getDate());
        rideDto.setLat(dto.getLat());
        rideDto.setLng(dto.getLng());

        try{
            rideService.add(rideDto);
            Integer id = rideService.addRecurrent(dto);
            return new ResponseEntity<>(id, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
