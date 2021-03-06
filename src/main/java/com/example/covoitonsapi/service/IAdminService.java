package com.example.covoitonsapi.service;

import com.example.covoitonsapi.dto.RideDto;
import com.example.covoitonsapi.dto.UserDto;
import com.example.covoitonsapi.entity.UserEntity;

import java.util.List;

public interface IAdminService {
    List<UserDto> getAllUsers();
    UserDto toDto(UserEntity entity);
    List<RideDto> getAllRides();
    Boolean exist(Integer id);
    Integer updateIsAdmin(Integer id, Boolean is_admin)throws Exception;
    Boolean rideExist(Integer id);
    void deleteRide(Integer id);
    Integer updateEnabled(Integer id, Boolean enabled)throws Exception;
}
