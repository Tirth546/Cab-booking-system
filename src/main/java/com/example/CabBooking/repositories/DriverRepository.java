package com.example.CabBooking.repositories;

import com.example.CabBooking.entities.Booking;
import com.example.CabBooking.entities.Customer;
import com.example.CabBooking.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;
import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    public Driver findDriverByCustomer(Customer customer);

    public Driver findDriverByCurrentBookingAndVehicle_CapacityGreaterThanEqualAndLastLocation(Booking booking, int peoples, String location);


    public List<Driver> findDriverByCurrentBookingAndVehicleCapacityGreaterThan(Booking booking, int peoples);

}
