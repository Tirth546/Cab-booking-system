package com.example.CabBooking.controllers;

import com.example.CabBooking.entities.Booking;
import com.example.CabBooking.entities.Customer;
import com.example.CabBooking.entities.Driver;
import com.example.CabBooking.services.BookingService;
import com.example.CabBooking.services.CustomerService;
import com.example.CabBooking.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DriverController {
    @Autowired
    private DriverService driverService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private BookingService bookingService;
    @PostMapping("/driver/register")
    public ResponseEntity<String> registerDriver(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findCustomerByEmail(email);
        driverService.registerDriver(customer);
        return new ResponseEntity<>("Successfully Registered as Driver!!", HttpStatus.OK);
    }

    @GetMapping("/driver/booking")
    public List<Booking> viewBooking(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findCustomerByEmail(email);
        Driver driver = driverService.findDriverByCustomer(customer);
        List<Booking> booking = bookingService.findDriverBooking(driver);
        return booking;
    }
}
