package com.example.CabBooking.repositories;

import com.example.CabBooking.entities.Booking;
import com.example.CabBooking.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findCustomerByEmail(String email);

    public Customer findCustomerByPhone(int phone);

    public boolean existsByEmail(String email);

    public boolean existsByPhone(int phone);

}
