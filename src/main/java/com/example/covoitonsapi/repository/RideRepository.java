package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.RideEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<RideEntity, Integer> {
}
