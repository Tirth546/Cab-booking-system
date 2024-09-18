package com.example.CabBooking.controllers;

import com.example.CabBooking.dto.BookingDto;
import com.example.CabBooking.dto.VerifyDto;
import com.example.CabBooking.entities.Booking;
import com.example.CabBooking.entities.Customer;
import com.example.CabBooking.entities.Driver;
import com.example.CabBooking.entities.Trip;
import com.example.CabBooking.services.BookingService;
import com.example.CabBooking.services.CustomerService;
import com.example.CabBooking.services.DriverService;
import com.example.CabBooking.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private TripService tripService;
    @Autowired
    private DriverService driverService;

    @PostMapping("/booking")
    public ResponseEntity<String> createBooking(@RequestBody BookingDto bookingDto){
        Trip trip = tripService.findTripByPickupAndDestination(bookingDto.getPickup().toLowerCase(), bookingDto.getDestination().toLowerCase());
        if(trip == null){
            trip = tripService.createTrip(bookingDto);
        }
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findCustomerByEmail(email);
        Driver driver = driverService.findDriverForBooking(bookingDto.getPickup(), bookingDto.getPeoples());
        if (driver == null) {
            return new ResponseEntity<>("No Driver Found", HttpStatus.BAD_REQUEST);
        }
        Booking booking = bookingService.createBooking(trip, customer, driver, bookingDto);
        driver.setCurrentBooking(booking);
        driverService.updateDriver(driver);
        return new ResponseEntity<>("Booking successfully created\n" + "Pin for booking: " + booking.getPin() + "\n" + "Driver Details: " + driver, HttpStatus.OK);

    }

    @GetMapping("/booking/{id}")
    public Booking showBooking(@PathVariable int id){
        Optional<Booking> booking = bookingService.findBookingById(id);
        if(booking.isPresent()){
            return booking.get();
        }
        return null;
    }

    @PostMapping("/booking/verify")
    public ResponseEntity<String> verifyPin(@RequestBody VerifyDto verifyDto){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findCustomerByEmail(email);
        Driver driver = driverService.findDriverByCustomer(customer);
        Booking booking = driver.getCurrentBooking();

        if(booking == null){
            return new ResponseEntity<>("Provide valid Booking id!!", HttpStatus.BAD_REQUEST);
        }

        if(booking.getPin() != verifyDto.getPin()){
            return new ResponseEntity<>("Invalid Pin!!", HttpStatus.BAD_REQUEST);
        }
        booking.setPickup_time(new Timestamp(System.currentTimeMillis()));
        booking.setBooking_status("On Way");
        bookingService.updateBooking(booking);
        return new ResponseEntity<>("Booking verified!!", HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/booking/end")
    public ResponseEntity<String> endBooking(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findCustomerByEmail(email);
        Driver driver = driverService.findDriverByCustomer(customer);
        Booking booking = driver.getCurrentBooking();
        if(booking == null){
            return new ResponseEntity<>("No current booking!!", HttpStatus.BAD_REQUEST);
        }
        booking.setDrop_time(new Timestamp(System.currentTimeMillis()));
        booking.setBooking_status("Complete");
        bookingService.updateBooking(booking);
        driver.setCurrentBooking(null);
        driver.setLastLocation(booking.getTrip().getDestination());
        driverService.updateDriver(driver);
        return new ResponseEntity<>("Ride Completed!!", HttpStatus.OK);
    }

    @DeleteMapping("/booking/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable int id){
        bookingService.deleteBooking(id);
        return new ResponseEntity<>("Booking Canceled Successfully!!", HttpStatus.OK);
    }
}
