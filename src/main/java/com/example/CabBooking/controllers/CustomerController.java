package com.example.CabBooking.controllers;

import com.example.CabBooking.dto.SignUpDto;
import com.example.CabBooking.entities.Booking;
import com.example.CabBooking.entities.Customer;
import com.example.CabBooking.entities.Driver;
import com.example.CabBooking.services.BookingService;
import com.example.CabBooking.services.CustomerService;
import com.example.CabBooking.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private BookingService bookingService;


    @GetMapping("/me/booking")
    public ResponseEntity<List<String>> showBooking(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findCustomerByEmail(email);
        List<Booking> bookings = bookingService.findBooking(customer);
        List<String> book = bookings.stream().map(booking -> booking.toString()).toList();
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<String> showDetails(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findCustomerByEmail(email);
        Driver driver = driverService.findDriverByCustomer(customer);
        if(driver == null){
            return new ResponseEntity<>(customer.toString(), HttpStatus.OK);
        }
        return new ResponseEntity<>(driver.toString(), HttpStatus.OK);
    }

}
