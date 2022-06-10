package com.example.covoitonsapi.entity;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "recurrents_rides")
public class RecurrentRideEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_driver")
    private UserEntity userEntity;

    @Column(name = "home_to_office")
    private Boolean home_to_office;

    @Column(name = "date")
    private Date date;

    @Column(name="arrival_time")
    private LocalTime arrivalTime;

    @Column(name ="places")
    private Integer places;

    @Column(name="street")
    private String street;

    @Column(name="post_code")
    private Integer post_code;

    @Column(name="city")
    private String city;

    @Column(name = "enable")
    private Boolean enable;

    @Column(name = "end_date")
    private Date end_date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_delais")
    private DelaisEntity delais;

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

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
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

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
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

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public DelaisEntity getDelais() {
        return delais;
    }

    public void setDelais(DelaisEntity delais) {
        this.delais = delais;
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
