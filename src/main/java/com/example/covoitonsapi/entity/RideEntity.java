package com.example.covoitonsapi.entity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="rides")
public class RideEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "departure_time")
    private Date departure_time;

    @Column(name = "date")
    private Date date;

    @Column(name = "home_to_office")
    private Boolean home_to_office;

    @Column(name ="places")
    private Integer places;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_driver")
    private UserEntity userEntity;

    @Column(name="street")
    private String street;

    @Column(name="post_code")
    private Integer post_code;

    @Column(name="city")
    private String city;
    
    @Column(name="arrival_time")
    private LocalTime arrivalTime;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="id_drive", referencedColumnName = "id")
    private List<RidesUsersEntity> ridesUsersEntityList;

    @Column(name="lat")
    private Float lat;

    @Column(name="lng")
    private Float lng;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Date getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(Date departure_time) {
        this.departure_time = departure_time;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public List<RidesUsersEntity> getRidesUsersEntityList() {
        return ridesUsersEntityList;
    }

    public void setRidesUsersEntityList(List<RidesUsersEntity> ridesUsersEntityList) {
        this.ridesUsersEntityList = ridesUsersEntityList;
    }

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
}
