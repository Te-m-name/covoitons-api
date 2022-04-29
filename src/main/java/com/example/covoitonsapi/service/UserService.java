package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.entity.UserEntity;
import com.example.covoitonsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository repository;

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
        entity.setPassword(dto.getPassword());
        entity.setUpdated_at(LocalDateTime.now());
        entity.setCreated_at(LocalDateTime.now());
        entity.setIs_admin(false);

        repository.saveAndFlush(entity);

        return true;
    }
}
