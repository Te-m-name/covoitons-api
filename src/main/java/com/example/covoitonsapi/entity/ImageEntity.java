package com.example.covoitonsapi.entity;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class ImageEntity {
    public ImageEntity() {
        super();
    }

    public ImageEntity(String name, String type, byte[] picByte, Integer userId) {
        this.name = name;
        this.type = type;
        this.picByte = picByte;
        this.userId = userId;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "picByte", length = 1000)
    private byte[] picByte;

    @Column(name = "user_id")
    private Integer userId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getPicByte() {
        return picByte;
    }

    public void setPicByte(byte[] picByte) {
        this.picByte = picByte;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
