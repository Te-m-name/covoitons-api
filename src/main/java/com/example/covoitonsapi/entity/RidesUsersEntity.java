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

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_passenger")
    private UserEntity user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_ride")
    private RideEntity ride;

    @Column(name = "accepted")
    private Boolean accepted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public RideEntity getRide() {
        return ride;
    }

    public void setRide(RideEntity ride) {
        this.ride = ride;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
