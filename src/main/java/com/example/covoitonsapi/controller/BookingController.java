package com.example.covoitonsapi.controller;

import com.example.covoitonsapi.dto.BookingDto;
import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "https://covoitons-client-team-name4.vercel.app"})
@RestController
@RequestMapping("booking")
public class BookingController {

    @Autowired
    private BookingService service;

    @PostMapping("bookARide")
    public ResponseEntity<Boolean> bookeARide(@RequestBody BookingDto dto){

        try{
            service.book(dto);
            return  new ResponseEntity(true, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("cancelBooking/{id}")
    public ResponseEntity<Boolean> cancelABooking(@PathVariable Integer id){

        try{
            service.canceleBooking(id);
            return new ResponseEntity(true, HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @PatchMapping("acceptBooking/{id}")
    public ResponseEntity<Boolean> acceptBooking(@PathVariable Integer id){

        try{

            service.acceptBooking(id);
            return new ResponseEntity(true, HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("declineBooking/{id}")
    public ResponseEntity<Boolean> declineBooking(@PathVariable Integer id){

        try{

            service.declineBooking(id);
            return new ResponseEntity(true, HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("getBookingOnMyRide/{id}")
    public ResponseEntity<List<BookingDto>> getAllBooking(@PathVariable String id) {

        Integer ID = Integer.parseInt(id);

        try {

            return new ResponseEntity(service.getBookingOnMyRide(ID), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getMyBookingRequest/{id}")
    public ResponseEntity<List<BookingDto>> getMyBooking(@PathVariable String id) {

        Integer ID = Integer.parseInt(id);

        try {

            return new ResponseEntity(service.getMyBooking(ID), HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
