package com.example.covoitonsapi.controller;

import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("getAllUsers")
    public ResponseEntity<List<UserDto>>getAllUsers(){
        try {
            return new ResponseEntity(adminService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("getAllRides")
    public ResponseEntity<List<RideDto>>getAllRides(){
        try {
            return new ResponseEntity(adminService.getAllRides(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
