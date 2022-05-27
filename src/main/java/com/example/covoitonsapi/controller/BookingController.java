package com.example.covoitonsapi.controller;

import com.example.covoitonsapi.dto.BookingDto;
import com.example.covoitonsapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("booking")
public class BookingController {

    @Autowired
    private BookingService service;

    @PostMapping("book")
    public ResponseEntity<Boolean> reserved(@RequestBody BookingDto dto){

        try{
            service.reserved(dto);
            return  new ResponseEntity(true, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("cancelBooking")
    public ResponseEntity<Boolean> canceled(@RequestBody BookingDto dto){

        try{
            service.canceled(dto);
            return new ResponseEntity(true, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
