package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.entity.RideEntity;
import com.example.covoitonsapi.entity.UserEntity;
import com.example.covoitonsapi.repository.RideRepository;
import com.example.covoitonsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService implements IAdminService{


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RideRepository rideRepository;

    @Override
    public List<UserDto> getAllUsers() {

        List<UserEntity> liste = userRepository.findAll();
        return liste.stream().map(e->toDto(e)).collect(Collectors.toList());
    }

    @Override
    public UserDto toDto(UserEntity entity) {

        UserDto dto = new UserDto();
        dto.setId(entity.getID());
        dto.setFirstname(entity.getFirstname());
        dto.setLastname(entity.getLastname());
        dto.setEmail(entity.getEmail());
        dto.setIs_admin(entity.getIs_admin());
        dto.setEmployee_code(entity.getEmployee_code());
        return dto;
    }

    @Override
    public List<RideDto> getAllRides() {
        List<RideEntity> liste = rideRepository.findAll();
        return liste.stream().map(e->rideToDto(e)).collect(Collectors.toList());
    }

    public RideDto rideToDto(RideEntity entity){
        RideDto dto = new RideDto();
        dto.setStreet(entity.getStreet());
        dto.setCity(entity.getCity());
        dto.setId_ride(entity.getId());
        dto.setPost_code(entity.getPost_code());
        return dto;
    }


}
