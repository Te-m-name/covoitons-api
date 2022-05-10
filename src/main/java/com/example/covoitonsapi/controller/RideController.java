package com.example.covoitonsapi.controller;

import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("ride")
public class RideController {

    @Autowired
    private RideService rideService;

    @GetMapping("getARide")
    public ResponseEntity<RideDto> getARide(@RequestParam String id){

        // convertir l'id en Integer
        Integer ID = Integer.parseInt(id);

        // verifier si le tajet existe ou non
        if (!rideService.exist(ID))
            return new ResponseEntity("le trajet n'existe pas", HttpStatus.NOT_FOUND);

        // on recupere le trajet transformé en amont en DTO
        RideDto dto  = rideService.getById(ID);

        return  new ResponseEntity(dto, HttpStatus.OK);
    }
}
