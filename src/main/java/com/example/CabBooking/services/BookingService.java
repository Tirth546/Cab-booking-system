package com.example.CabBooking.services;

import com.example.CabBooking.dto.BookingDto;
import com.example.CabBooking.entities.Booking;
import com.example.CabBooking.entities.Customer;
import com.example.CabBooking.entities.Driver;
import com.example.CabBooking.entities.Trip;
import com.example.CabBooking.repositories.BookingRepository;
import com.example.CabBooking.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.Math.*;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    public Booking createBooking(Trip trip, Customer customer, Driver driver, BookingDto bookingDto){
        Booking booking = new Booking();
        booking.setBooking_status("Pending");
        booking.setCustomer(customer);
        booking.setTrip(trip);
        booking.setDriver(driver);
        booking.setPeoples(bookingDto.getPeoples());
        int pin = (int) floor(100000 + random() * 900000);
        booking.setPin(pin);
        return bookingRepository.save(booking);
    }

    public void updateBooking(Booking booking){
        bookingRepository.save(booking);
    }

    public List<Booking> findBooking(Customer customer){
        return bookingRepository.findBookingsByCustomer(customer);
    }

    public Optional<Booking> findBookingById(int id){
        return bookingRepository.findById(id);
    }

    public void deleteBooking(int id){
        bookingRepository.deleteById(id);
    }

    public List<Booking> findDriverBooking(Driver driver){
        return bookingRepository.findBookingsByDriver(driver);
    }
}
