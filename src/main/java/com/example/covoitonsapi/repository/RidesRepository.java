package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.RidesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RidesRepository extends JpaRepository<RidesEntity, Integer> {

}
