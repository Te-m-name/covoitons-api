package com.example.covoitonsapi.dto;

public class UserDto {
    private String lastname;
    private String firstname;
    private String email;
    private Integer employee_code;
    private String password;
    private String confirm_password;

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(String confirm_password) {
        this.confirm_password = confirm_password;
    }
}
