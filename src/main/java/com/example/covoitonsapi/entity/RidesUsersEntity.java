package com.example.covoitonsapi.entity;

import org.springframework.web.bind.annotation.CookieValue;

import javax.persistence.*;

@Entity
@Table(name="rides_users")
public class RidesUsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="id_user")
    private Integer id_user;

    @Column(name="id_ride")
    private Integer id_ride;

    @Column(name = "accepted")
    private Boolean accepted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
