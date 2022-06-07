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

    @Autowired
    ImageRepository imageRepository;

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
    public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {

        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        ImageEntity img = new ImageEntity(file.getOriginalFilename(), file.getContentType(),
                compressBytes(file.getBytes()), 1);
        imageRepository.save(img);
        return ResponseEntity.status(HttpStatus.OK);
    }

    @GetMapping(path = { "/get/{imageName}" })
    public ImageEntity getImage(@PathVariable("imageName") Integer userId) throws IOException {

        final Optional<ImageEntity> retrievedImage = imageRepository.findByUserId(userId);
        ImageEntity img = new ImageEntity(retrievedImage.get().getName(), retrievedImage.get().getType(),
                decompressBytes(retrievedImage.get().getPicByte()), retrievedImage.get().getUserId());
        return img;
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException ioe) {
        } catch (DataFormatException e) {
        }
        return outputStream.toByteArray();
    }
}
