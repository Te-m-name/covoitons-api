package com.example.covoitonsapi.repository;

import com.example.covoitonsapi.entity.RidesUsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<RidesUsersEntity, Integer> {
}
