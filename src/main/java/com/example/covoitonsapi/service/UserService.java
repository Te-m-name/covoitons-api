package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.entity.UserEntity;
import com.example.covoitonsapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService implements IUserService, UserDetailsService {

    @Autowired
    private UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = repository.findByEmail(email);
        if(user == null) {
            log.error("User not found in database");
            throw new UsernameNotFoundException("User not found in database");
        } else {
            log.info("User found in database: {}", email);
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
    public Boolean add(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setFirstname(dto.getFirstname());
        entity.setLastname(dto.getLastname());
        entity.setEmail(dto.getEmail());
        entity.setEmployee_code(dto.getEmployee_code());
        entity.setPassword(passwordEncoder.encode(dto.getPassword()));
        entity.setUpdated_at(LocalDateTime.now());
        entity.setCreated_at(LocalDateTime.now());
        entity.setIs_admin(false);

        repository.saveAndFlush(entity);

        return true;
    }

    @Override
    public UserDto getCurrentUser() {
        UserEntity currentUser = repository.findByEmail((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        UserDto dto = new UserDto();
        dto.setEmail(currentUser.getEmail());
        dto.setFirstname(currentUser.getFirstname());
        dto.setLastname(currentUser.getLastname());
        dto.setIs_admin(currentUser.getIs_admin());

        return dto;
    }


}
