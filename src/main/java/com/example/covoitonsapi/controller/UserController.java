package com.example.covoitonsapi.controller;

import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("add")
    public ResponseEntity<Boolean> add(@RequestBody UserDto dto) throws Exception{

        if(!dto.getPassword().equals(dto.getConfirm_password())){
            return new ResponseEntity("Mot de passe incorrect", HttpStatus.BAD_REQUEST);
        }
        if(!dto.getEmail().endsWith("@ipipoe.com")){
            return new ResponseEntity("Merci d'utiliser votre email d'entreprise", HttpStatus.BAD_REQUEST);
        }

        try{
            service.add(dto);
            return new ResponseEntity(true, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
