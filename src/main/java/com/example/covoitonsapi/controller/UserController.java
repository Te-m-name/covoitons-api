package com.example.covoitonsapi.controller;

import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.service.RegistrationService;
import com.example.covoitonsapi.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("user")
@Slf4j
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService service;

    private final RegistrationService registrationService;

    @PostMapping("add")
    public ResponseEntity<String> add(@RequestBody UserDto dto) throws Exception{

        if(!dto.getPassword().equals(dto.getConfirm_password())){
            return new ResponseEntity("Mot de passe incorrect", HttpStatus.BAD_REQUEST);
        }
        if(!dto.getEmail().endsWith("@ipipoe.com")){
            return new ResponseEntity("Merci d'utiliser votre email d'entreprise", HttpStatus.BAD_REQUEST);
        }

        try{
            String token = registrationService.register(dto);
            return new ResponseEntity(token, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }

    @GetMapping("current-user")
    public ResponseEntity<UserDto> getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() != "anonymousUser") {
            try {
                UserDto currentUser = service.getCurrentUser();
                return new ResponseEntity(currentUser, HttpStatus.OK);
            } catch(Exception e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity(null, HttpStatus.OK);
        }

    }


}
