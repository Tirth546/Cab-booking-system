package com.example.CabBooking.services;

import com.example.CabBooking.entities.Customer;
import com.example.CabBooking.entities.Driver;
import com.example.CabBooking.entities.Vehicle;
import com.example.CabBooking.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;
    public void registerVehicle(Vehicle vehicle, Driver driver){
        System.out.println(vehicle);
        driver.setVehicle(vehicle);
        vehicleRepository.save(vehicle);
    }
}
