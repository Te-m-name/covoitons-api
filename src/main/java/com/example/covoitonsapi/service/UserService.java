package com.example.covoitonsapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.entity.ConfirmationEntity;
import com.example.covoitonsapi.entity.EmployeeEntity;
import com.example.covoitonsapi.entity.UserEntity;
import com.example.covoitonsapi.repository.EmployeeRepository;
import com.example.covoitonsapi.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private UserRepository repository;
    private final ConfirmationService confirmationService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, IllegalStateException {
        UserEntity user = repository.findByEmail(email);
        if(user == null) {
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        } else {
            log.info("User found in database: {}", email);
        }

        if(!user.getEnabled()) {
            log.error("User not enabled");
            throw new IllegalStateException("Veuillez confirmer votre compte");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(user.getIs_admin() == true) {
            authorities.add(new SimpleGrantedAuthority("admin"));
        } else {
            authorities.add(new SimpleGrantedAuthority("user"));
        }

        return new org.springframework.security.core.userdetails.User(email, user.getPassword(), authorities);
    }

    @Override
    public List<UserDto> getAllUser() {
        return null;
    }

    @Override
    public String add(UserEntity entity) throws Exception {

        Boolean userExists = repository.existsByEmail(entity.getEmail());

        if (userExists) {
            throw new IllegalStateException("Email déjà utilisé");
        }

        EmployeeEntity emp;
        try {
            emp = employeeRepository.findById(entity.getEmployee_code()).get();
        } catch (Exception e){
            throw new Exception ("code employé inconnu");
        }

        if(entity.getEmail().equals(emp.getEmail())){
            repository.saveAndFlush(entity);

            String token = UUID.randomUUID().toString();
            ConfirmationEntity confirmationToken = new ConfirmationEntity(
                    token,
                    LocalDateTime.now(),
                    LocalDateTime.now().plusMinutes(60),
                    entity
            );

            confirmationService.saveConfirmationToken(confirmationToken);
            return token;
        } else {
            throw new Exception("Email / code employé incorrect");
        }
    }

    public int enableUser(String email) {
        return repository.enableUser(email);
    }

    @Override
    public UserEntity getUser(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public UserDto getCurrentUser() {
        UserEntity currentUser = repository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        UserDto dto = new UserDto();
        dto.setEmail(currentUser.getEmail());
        dto.setFirstname(currentUser.getFirstname());
        dto.setLastname(currentUser.getLastname());
        dto.setIs_admin(currentUser.getIs_admin());
        dto.setId(currentUser.getID());

        return dto;
    }

    @Override
    public void refreshToken(HttpServletRequest request, HttpServletResponse response, String authorizationHeader) throws IOException {
        String refresh_token = authorizationHeader.substring("Bearer ".length());
        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT decodedJWT = verifier.verify(refresh_token);
        String email = decodedJWT.getSubject();
        UserEntity user = this.getUser(email);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(user.getIs_admin() == true) {
            authorities.add(new SimpleGrantedAuthority("admin"));
        } else {
            authorities.add(new SimpleGrantedAuthority("user"));
        }

        String access_token = JWT.create()
                .withSubject(user.getEmail())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                .sign(algorithm);


        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", access_token);
        tokens.put("refresh_token", refresh_token);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }

}
