package com.example.covoitonsapi.dto;

public class ReservationDto {

    private Integer user_id;
    private Integer ride_id;

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getRide_id() {
        return ride_id;
    }

    public void setRide_id(Integer ride_id) {
        this.ride_id = ride_id;
    }
}
