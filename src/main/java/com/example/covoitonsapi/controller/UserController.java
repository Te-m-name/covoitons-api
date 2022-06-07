package com.example.covoitonsapi.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.entity.ImageEntity;
import com.example.covoitonsapi.entity.UserEntity;
import com.example.covoitonsapi.repository.ImageRepository;
import com.example.covoitonsapi.service.RegistrationService;
import com.example.covoitonsapi.service.UserService;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = {"http://localhost:4200", "https://covoitons-client-team-name4.vercel.app"})
@RestController
@RequestMapping("user")
@Slf4j
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService service;

    private final RegistrationService registrationService;

    @PostMapping("add")
    public ResponseEntity<Boolean> add(@RequestBody UserDto dto) throws Exception{

        if(!dto.getPassword().equals(dto.getConfirm_password())){
            return new ResponseEntity("Mot de passe incorrect", HttpStatus.BAD_REQUEST);
        }
        if(!dto.getEmail().endsWith("@yopmail.com")){
            return new ResponseEntity("Merci d'utiliser votre email d'entreprise", HttpStatus.BAD_REQUEST);
        }

        try{
            registrationService.register(dto);
            return new ResponseEntity(true, HttpStatus.OK);
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

    @GetMapping("token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                service.refreshToken(request, response, authorizationHeader);
            } catch (Exception exception) {
                log.error("Error logging in: {}", exception.getMessage());
                response.setHeader("error", exception.getMessage());
                response.setStatus(FORBIDDEN.value());
                Map<String, String> error = new HashMap<>();
                error.put("error_message", exception.getMessage());
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }

    @PostMapping("/upload")
    public ResponseEntity uploadImage(@RequestParam("imageFile") MultipartFile file) {
        Integer id = service.getCurrentUser().getId();
        if (service.ImgExist(id)) {
            try {
                Integer img = service.updateImageProfile(file);
                return new ResponseEntity(null, HttpStatus.OK);
            } catch(Exception e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }

        try {
            ImageEntity img = service.uploadImageProfile(file);
            return new ResponseEntity(null, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{imageName}")
    public ResponseEntity getImage(@PathVariable("imageName") Integer userId) {
        try {
            ImageEntity img = service.getImage(userId);
            return new ResponseEntity(img, HttpStatus.OK);
        } catch(IOException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
