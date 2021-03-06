package com.example.covoitonsapi.dto;

import java.time.LocalTime;
import java.util.Date;

public class BookingDto {

    private Integer id;
    private Integer user_id;
    private Integer ride_id;
    private Boolean accepted;

    private String userIdentity;

    private String  driverIdentity;

    private String departure;

    private String arrival;

    private Date departure_date;

    private LocalTime arrival_time;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public String getDriverIdentity() {
        return driverIdentity;
    }

    public void setDriverIdentity(String driverIdentity) {
        this.driverIdentity = driverIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public Date getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Date departure_date) {
        this.departure_date = departure_date;
    }

    public LocalTime getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(LocalTime arrival_time) {
        this.arrival_time = arrival_time;
    }
}
