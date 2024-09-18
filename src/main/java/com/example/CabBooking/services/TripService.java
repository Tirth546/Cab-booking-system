package com.example.CabBooking.services;

import com.example.CabBooking.dto.BookingDto;
import com.example.CabBooking.entities.Trip;
import com.example.CabBooking.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {

    @Autowired
    TripRepository tripRepository;
    public Trip createTrip(BookingDto bookingDto){
        Trip trip = new Trip();
        trip.setDestination(bookingDto.getDestination());
        trip.setPickup(bookingDto.getPickup());
        trip.setDistance(bookingDto.getDistance());
        double fare = 10.0 + bookingDto.getDistance() * 3.5;
        trip.setFare(fare);
        return tripRepository.save(trip);
    }

    public boolean existsTripByPickupAndDestination(String pickup, String destination){
        return tripRepository.existsTripByPickupAndDestination(pickup.toLowerCase(), destination.toLowerCase());
    }

    public Trip findTripByPickupAndDestination(String pickup, String destination){
        return tripRepository.findTripByPickupAndDestination(pickup.toLowerCase(), destination.toLowerCase());
    }
}
