package com.example.covoitonsapi.controller;

import com.example.covoitonsapi.dto.BookingDto;
import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("booking")
public class BookingController {

    @Autowired
    private BookingService service;

    @PostMapping("book")
    public ResponseEntity<Boolean> booked(@RequestBody BookingDto dto){

        try{
            service.book(dto);
            return  new ResponseEntity(true, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("cancelBooking/{id}")
    public ResponseEntity<Boolean> canceled(@PathVariable Integer id){

        try{
            service.canceleBooking(id);
            return new ResponseEntity(true, HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("acceptBooking/{id}")
    public ResponseEntity<Boolean> accepted(@PathVariable Integer id){

        try{

            service.acceptBooking(id);
            return new ResponseEntity(true, HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping("getMine/{id}")
    public ResponseEntity<List<BookingDto>> getMine(@PathVariable String id) {

        Integer ID = Integer.parseInt(id);

        try {

            return new ResponseEntity(service.getBookingOnMyRide(ID), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
