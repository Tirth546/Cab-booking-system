package com.example.CabBooking.controllers;

import com.example.CabBooking.entities.Customer;
import com.example.CabBooking.entities.Driver;
import com.example.CabBooking.entities.Vehicle;
import com.example.CabBooking.services.CustomerService;
import com.example.CabBooking.services.DriverService;
import com.example.CabBooking.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VehicleController {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    CustomerService customerService;
    @Autowired
    DriverService driverService;

    @PostMapping("/vehicle/register")
    public ResponseEntity<String> registerVehicle(@RequestBody Vehicle vehicle){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Customer customer = customerService.findCustomerByEmail(email);
        Driver driver = driverService.findDriverByCustomer(customer);
        if(driver == null){
            return new ResponseEntity<>("First Register as Driver!!", HttpStatus.BAD_REQUEST);
        }
        vehicleService.registerVehicle(vehicle, driver);
        return new ResponseEntity<>("Successfully register your vehicle!!", HttpStatus.OK);
    }

}
