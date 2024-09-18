package com.example.CabBooking.repositories;

import com.example.CabBooking.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripRepository extends JpaRepository<Trip, Integer> {
    public boolean existsTripByPickupAndDestination(String destination, String pickup);
    public Trip findTripByPickupAndDestination(String destination, String pickup);

}
