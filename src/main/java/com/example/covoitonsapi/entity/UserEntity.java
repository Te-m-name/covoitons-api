package com.example.covoitonsapi.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer ID;

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="employee_code")
    private Integer employee_code;

    @Column(name="is_admin")
    private Boolean is_admin;

    @Column(name="enabled")
    private Boolean enabled;

    @Column(name="created_at")
    private LocalDateTime created_at;

    @Column(name="updated_at")
    private LocalDateTime updated_at;

    @Column(name="deleted_at")
    private LocalDateTime deleted_at;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="id_driver", referencedColumnName = "id")
    private List<RideEntity> rideEntityList;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="id_user", referencedColumnName = "id")
    private List<RidesUsersEntity> ridesUsersEntityList;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_sender", referencedColumnName = "id")
//    private List<MessagesEntity> messagesEntities;


    public List<RidesUsersEntity> getRidesUsersEntityList() {
        return ridesUsersEntityList;
    }

    public void setRidesUsersEntityList(List<RidesUsersEntity> ridesUsersEntityList) {
        this.ridesUsersEntityList = ridesUsersEntityList;
    }

    public List<RideEntity> getRideEntityList() {
        return rideEntityList;
    }

    public void setRideEntityList(List<RideEntity> rideEntityList) {
        this.rideEntityList = rideEntityList;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEmployee_code() {
        return employee_code;
    }

    public void setEmployee_code(Integer employee_code) {
        this.employee_code = employee_code;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public LocalDateTime getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(LocalDateTime deleted_at) {
        this.deleted_at = deleted_at;
    }


    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
