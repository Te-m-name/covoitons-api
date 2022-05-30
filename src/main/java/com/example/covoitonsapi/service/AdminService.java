package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.entity.RideEntity;
import com.example.covoitonsapi.entity.UserEntity;
import com.example.covoitonsapi.repository.RideRepository;
import com.example.covoitonsapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        dto.setEnabled(entity.getEnabled());
        return dto;
    }

    @Override
    public List<RideDto> getAllRides() {
        List<RideEntity> liste = rideRepository.findAll();
        return liste.stream().map(e->rideToDto(e)).collect(Collectors.toList());
    }

    public Boolean exist(Integer id) {
        return userRepository.existsById(id);
    }

    @Override
    public Integer updateIsAdmin(Integer id, Boolean is_admin)throws Exception{
        UserEntity entity = new UserEntity();
        entity.setID(id);
        entity.setIs_admin(is_admin);
        try{
            userRepository.updateIsAdmin(entity.getIs_admin(), entity.getID());
        }catch(Exception e){
            throw new Exception();
        }
        return 1;
    }

    @Override
    public Boolean rideExist(Integer id) {
        return rideRepository.existsById(id);
    }

    @Override
    public void deleteRide(Integer id) {
        rideRepository.deleteById(id);
    }

    public RideDto rideToDto(RideEntity entity){

        UserEntity user = entity.getUserEntity();

        RideDto dto = new RideDto();
        dto.setStreet(entity.getStreet());
        dto.setCity(entity.getCity());
        dto.setId_ride(entity.getId());
        dto.setPost_code(entity.getPost_code());
        dto.setDeparture_date(entity.getDeparture_time());
        dto.setPlaces(entity.getPlaces());
        dto.setDriverFirstname(user.getFirstname());
        dto.setDriverLastname(user.getLastname());
        dto.setHome_to_office(entity.getHome_to_office());
        return dto;
    }

    @Override
    public Integer updateEnabled(Integer id, Boolean enabled)throws Exception{
        UserEntity entity = new UserEntity();
        entity.setID(id);
        entity.setEnabled(enabled);
        try{
            userRepository.updateEnabled(entity.getEnabled(), entity.getID());
        }catch(Exception e){
            throw new Exception();
        }
        return 1;
    }
}
