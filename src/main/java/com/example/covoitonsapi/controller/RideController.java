package com.example.covoitonsapi.controller;

import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("ride")

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

        // on recupere le trajet transformé en amont en DTO
        RideDto dto = rideService.getById(ID);

        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity<Integer> add(@RequestBody RideDto dto){

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

    @GetMapping("searchCity/{city}")
    public ResponseEntity<List<RideDto>> searchByCity(@PathVariable String city) {
        try {
            return new ResponseEntity(rideService.getRideByCity(city), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
