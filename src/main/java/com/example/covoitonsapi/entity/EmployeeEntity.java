package com.example.covoitonsapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="email")
    private String email;

    @Column(name="employee_code")
    private Integer employee_code;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getEmployee_code() {
        return employee_code;
    }

    public void setEmployee_code(Integer employee_code) {
        this.employee_code = employee_code;
    }
}
