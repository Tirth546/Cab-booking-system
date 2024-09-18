package com.example.CabBooking.entities;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Trip {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int trip_id;
    private String pickup;
    private String destination;
    private double distance;
    private double fare;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trip")
    private List<Booking>book;

    public int getTrip_id() {
        return trip_id;
    }

    public void setTrip_id(int trip_id) {
        this.trip_id = trip_id;
    }

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public List<Booking> getBook() {
        return book;
    }

    public void setBook(List<Booking> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "pickup='" + pickup + '\'' +
                ", destination='" + destination + '\'' +
                ", distance=" + distance +
                ", fare=" + fare +
                '}';
    }
}
