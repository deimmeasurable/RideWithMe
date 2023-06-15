package com.example.ridewithme.repository;

import com.example.ridewithme.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {



    Optional<Driver> findDriverByEmail(String email);

}
