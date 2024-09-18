package com.example.CabBooking.controllers;

import com.example.CabBooking.dto.LoginDto;
import com.example.CabBooking.dto.SignUpDto;
import com.example.CabBooking.entities.Customer;
import com.example.CabBooking.services.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.AuthProvider;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@Controller
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private CustomerService customerService;


    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/signup")
    public ResponseEntity<String> singup(@RequestBody SignUpDto signUpDto) throws  Exception{
        if(customerService.existsByPhone(signUpDto.getPhone()) || customerService.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email or Phone Already Registered!!", HttpStatus.BAD_REQUEST);
        }
        Customer customer = customerService.createCustomer(signUpDto);
        return new ResponseEntity<>("Successfully Created!!", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpServletRequest req){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        if(!authentication.isAuthenticated()){
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }
        SecurityContext sc = SecurityContextHolder.createEmptyContext();
        sc.setAuthentication(authentication);
        SecurityContextHolder.setContext(sc);
        HttpSession session = req.getSession(true);
        session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
        return new ResponseEntity<>("Successfully Logged In!!", HttpStatus.OK);
    }



}
