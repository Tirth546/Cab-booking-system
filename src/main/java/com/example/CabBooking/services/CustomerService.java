package com.example.CabBooking.services;

import com.example.CabBooking.dto.SignUpDto;
import com.example.CabBooking.entities.Booking;
import com.example.CabBooking.entities.Customer;
import com.example.CabBooking.entities.Driver;
import com.example.CabBooking.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    DriverService driverService;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Customer createCustomer(SignUpDto signUpDto){
        Customer customer = new Customer();
        customer.setName(signUpDto.getName());
        customer.setEmail(signUpDto.getEmail());
        customer.setPassword(bCryptPasswordEncoder.encode(signUpDto.getPassword()));
        customer.setDob(signUpDto.getDob());
        customer.setGender(signUpDto.getGender());
        customer.setPhone(signUpDto.getPhone());
        customer.setCity(signUpDto.getCity());

        customerRepository.save(customer);
        return customer;
    }

    public Customer findCustomerByPhone(int phone){
        return customerRepository.findCustomerByPhone(phone);
    }

    public boolean existsByEmail(String email){
        return customerRepository.existsByEmail(email);
    }

    public boolean existsByPhone(int phone){
        return customerRepository.existsByPhone(phone);
    }

    public Customer findCustomerByEmail(String email){
        return customerRepository.findCustomerByEmail(email);
    }
    public void deleteCustomer(int id){
        customerRepository.deleteById(id);
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findCustomerByEmail(username);
        if(customer == null){
            throw new UsernameNotFoundException(username);
        }
        String role;
        Driver driver = driverService.findDriverByCustomer(customer);
        if(driver == null){
            role = "CUSTOMER";
        }else{
            role = "DRIVER";
        }
        UserDetails user = User.withUsername(customer.getEmail()).password(customer.getPassword()).roles(role).build();
        return user;
    }

}
