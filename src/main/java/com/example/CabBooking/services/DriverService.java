package com.example.CabBooking.services;

import com.example.CabBooking.entities.Customer;
import com.example.CabBooking.entities.Driver;
import com.example.CabBooking.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DriverService {
    @Autowired
    private DriverRepository driverRepository;
    public void registerDriver(Customer customer){
        Driver driver = new Driver();
        driver.setCustomer(customer);
        driver.setLastLocation(customer.getCity());
        driverRepository.save(driver);
    }

    public Driver findDriverByCustomer(Customer customer){
        return driverRepository.findDriverByCustomer(customer);
    }

    public Driver findDriverForBooking(String lastLocation, int peoples){
        Driver driver = driverRepository.findDriverByCurrentBookingAndVehicle_CapacityGreaterThanEqualAndLastLocation(null, peoples, lastLocation);
        if(driver == null){
            driver = findRandomDriver(peoples);
        }
//        String[] destinations = drivers.stream().map(Driver::getLastLocation).toArray(String[]::new);
//        String[] origin = {lastLocation};
//        GeoApiContext context = GeoApi.getContext();
//        DistanceMatrix matrix = DistanceMatrixApi.getDistanceMatrix(context, origin, destinations)
//                .mode(TravelMode.DRIVING)
//                .trafficModel(TrafficModel.BEST_GUESS)
//                .departureTime(Instant.ofEpochSecond(System.currentTimeMillis() + 180000))
//                .await();
//        System.out.println("Hello3");
//        DistanceMatrixElement[] distanceMatrixElement = matrix.rows[0].elements;
//        List<Long> durations = Arrays.stream(distanceMatrixElement).map(element -> element.durationInTraffic.inSeconds).toList();
//        System.out.println(durations.toString());
//        int index = 0;
//        for(int i=1;i<durations.size();i++){
//            if(durations.get(index) > durations.get(i)){
//                index = i;
//            }
//        }
        return driver;
    }

    public Driver findRandomDriver(int peoples){
        List<Driver> drivers = driverRepository.findDriverByCurrentBookingAndVehicleCapacityGreaterThan(null, peoples);
        if(drivers.isEmpty()){
            return null;
        }
        Random random = new Random();
        int index = random.nextInt(drivers.size());
        return drivers.get(index);
    }

    public Driver updateDriver(Driver driver){
        return driverRepository.save(driver);
    }
}
