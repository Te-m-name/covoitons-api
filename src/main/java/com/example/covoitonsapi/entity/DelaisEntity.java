package com.example.covoitonsapi.entity;

import org.springframework.data.relational.core.sql.In;

import javax.persistence.*;

@Entity
@Table(name = "delais")
public class DelaisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "delais")
    private Integer delais;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDelais(Integer delais) {
        this.delais = delais;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDelais() {
        return delais;
    }
}
