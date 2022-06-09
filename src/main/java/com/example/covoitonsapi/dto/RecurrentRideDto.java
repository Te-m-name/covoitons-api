package com.example.covoitonsapi.dto;

import java.time.LocalTime;
import java.util.Date;

public class RecurrentRideDto {

    private Integer id_ride;
    private Integer id_driver;
    private Boolean home_to_office;
    private Date date;
    private LocalTime arrival_time;
    private Integer places;
    private String street;
    private Integer post_code;
    private String city;
    private Boolean enable;
    private Date end_Date;
    private Integer id_delais;
    private Float lat;
    private Float lng;

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public LocalTime getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(LocalTime arrival_time) {
        this.arrival_time = arrival_time;
    }

    public Integer getId_delais() {
        return id_delais;
    }

    public void setId_delais(Integer id_delais) {
        this.id_delais = id_delais;
    }

    public Integer getId_ride() {
        return id_ride;
    }

    public void setId_ride(Integer id_ride) {
        this.id_ride = id_ride;
    }

    public Integer getId_driver() {
        return id_driver;
    }

    public void setId_driver(Integer id_driver) {
        this.id_driver = id_driver;
    }

    public Boolean getHome_to_office() {
        return home_to_office;
    }

    public void setHome_to_office(Boolean home_to_office) {
        this.home_to_office = home_to_office;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getPlaces() {
        return places;
    }

    public void setPlaces(Integer places) {
        this.places = places;
    }

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

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Date getEnd_Date() {
        return end_Date;
    }

    public void setEnd_Date(Date end_Date) {
        this.end_Date = end_Date;
    }
}
