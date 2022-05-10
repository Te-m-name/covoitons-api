package com.example.covoitonsapi.controller;

import com.example.covoitonsapi.dto.RidesDto;
import com.example.covoitonsapi.entity.RidesEntity;
import com.example.covoitonsapi.repository.RidesRepository;
import com.example.covoitonsapi.service.RidesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("Trajets")
public class RideController {

    @Autowired
    private RidesRepository repository;
    @Autowired
    private RidesService service;

    @GetMapping("getAll")
    public List<RidesDto> getAll() {
        List<RidesEntity> ridesList = repository.findAll();

        return ridesList.stream().map(e -> service.toDto(e)).collect(Collectors.toList());
    }
}
