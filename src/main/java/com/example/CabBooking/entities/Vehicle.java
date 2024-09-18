package com.example.CabBooking.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int vehicle_id;
    private String vehicle_number;
    private int capacity;

    private String model;

    @Override
    public String toString() {
        return "Vehicle{" +
                ", vehicle_number='" + vehicle_number + '\'' +
                ", capacity=" + capacity +
                ", model='" + model + '\'' +
                '}';
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public void setVehicle_id(int vehicle_id) {
        this.vehicle_id = vehicle_id;
    }


    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}