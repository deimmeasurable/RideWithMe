package com.example.ridewithme.repository;

import com.example.ridewithme.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {
    Trip  findTripByEmail(String email);
}
