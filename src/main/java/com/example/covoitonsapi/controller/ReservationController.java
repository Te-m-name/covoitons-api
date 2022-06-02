package com.example.covoitonsapi.controller;

import com.example.covoitonsapi.dto.ReservationDto;
import com.example.covoitonsapi.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200", "https://covoitons-client.vercel.app"})
@RestController
@RequestMapping("reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("reservationRide")
    public ResponseEntity<Boolean> reserved(@RequestBody ReservationDto dto){

        try{
            reservationService.reserved(dto);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return  new ResponseEntity(true, HttpStatus.OK);
    }

    @PostMapping("cancelReservation")
    public ResponseEntity<Boolean> canceled(@RequestBody ReservationDto dto){

        try{
            reservationService.canceled(dto);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(true, HttpStatus.OK);
    }
}
