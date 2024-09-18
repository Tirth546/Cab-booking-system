package com.example.CabBooking.entities;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class Driver {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int driver_id;

    @OneToOne(cascade = CascadeType.ALL)
    private Customer customer;

    private String lastLocation;
    @OneToMany(mappedBy = "driver")
    private List<Booking>book;
//
    @OneToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

    public List<Booking> getBook() {
        return book;
    }

    public void setBook(List<Booking> book) {
        this.book = book;
    }

    public Booking getCurrentBooking() {
        return currentBooking;
    }

    public void setCurrentBooking(Booking currentBooking) {
        this.currentBooking = currentBooking;
    }

    @ManyToOne
    private Booking currentBooking = null;

    @Override
    public String toString() {
        return "Driver{" +
                "driver_id=" + driver_id +
                ", customer=" + customer +
                ", lastLocation='" + lastLocation + '\'' +
                ", vehicle=" + vehicle +
                '}';
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getLastLocation() {
        return lastLocation;
    }

    public void setLastLocation(String lastLocation) {
        this.lastLocation = lastLocation;
    }
}