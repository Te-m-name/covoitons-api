package com.example.covoitonsapi.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rides")
public class RidesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer ID;
    @Column(name = "departure_time")
    private LocalDateTime departureTime;
    @Column(name = "home_to_office")
    private Boolean home_to_office;
    @Column(name = "places")
    private Integer places;
    @Column(name = "street")
    private String street;
    @Column(name = "post_code")
    private Integer post_code;
    @Column(name = "city")
    private String city;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
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
}
