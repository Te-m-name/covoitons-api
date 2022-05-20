package com.example.covoitonsapi.dto;

import java.util.Date;

public class RideDto {

    private Integer id_ride;
    private String street;
    private Integer post_code;
    private String city;
    private String departure;
    private String arrival;
    private Date date;
    private Boolean home_to_office;
    private Integer places;
    private Integer id_user;

    //
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getPost_code() {
        return post_code;
    }

    public void setPost_code(Integer post_code) {
        this.post_code = post_code;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Boolean getHome_to_office() {
        return home_to_office;
    }

    public void setHome_to_office(Boolean home_to_office) {
        this.home_to_office = home_to_office;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_ride() {
        return id_ride;
    }

    public void setId_ride(Integer id_ride) {
        this.id_ride = id_ride;
    }
}
