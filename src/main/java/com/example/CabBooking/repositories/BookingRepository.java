package com.example.CabBooking.repositories;

import com.example.CabBooking.entities.Booking;
import com.example.CabBooking.entities.Customer;
import com.example.CabBooking.entities.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {
//    public void updateBookingByBooking_id(Booking booking);
        public List<Booking> findBookingsByCustomer(Customer customer);

        public List<Booking> findBookingsByDriver(Driver driver);
}
