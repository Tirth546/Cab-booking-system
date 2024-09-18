package com.example.CabBooking.entities;


import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int booking_id;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Timestamp booking_time = new Timestamp(System.currentTimeMillis());
    private int pin;
    private String booking_status;

    private int peoples;

    private Timestamp pickup_time;

    private Timestamp drop_time;

    @ManyToOne(cascade = CascadeType.ALL)
    private Trip trip;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL)
    private Driver driver;

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }



    public int getPeoples() {
        return peoples;
    }

    public void setPeoples(int peoples) {
        this.peoples = peoples;
    }

    public Timestamp getPickup_time() {
        return pickup_time;
    }

    public void setPickup_time(Timestamp pickup_time) {
        this.pickup_time = pickup_time;
    }

    public Timestamp getDrop_time() {
        return drop_time;
    }

    public void setDrop_time(Timestamp drop_time) {
        this.drop_time = drop_time;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public Timestamp getBooking_time() {
        return booking_time;
    }

    public void setBooking_time(Timestamp booking_time) {
        this.booking_time = booking_time;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "booking_time=" + booking_time +
                ", booking_status='" + booking_status + '\'' +
                ", peoples=" + peoples +
                ", pickup_time=" + pickup_time +
                ", drop_time=" + drop_time +
                ", trip=" + trip +
                ", customer=" + customer +
                ", driver=" + driver +
                '}';
    }
}