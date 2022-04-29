package com.example.covoitonsapi.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="user")
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

    @Column(name="is-admin")
    private Boolean is_admin;

    @Column(name="updated_at")
    private LocalDateTime updated_at;
}
