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
    private RideService service;

    @PostMapping("add")
    public ResponseEntity<Integer>add(@RequestBody RideDto dto){

        try{
            Integer id = service.add(dto);
            return  new ResponseEntity(id, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
