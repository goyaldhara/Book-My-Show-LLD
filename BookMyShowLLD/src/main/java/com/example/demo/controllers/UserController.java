package com.example.demo.controllers;

import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Theatre;
import com.example.demo.model.entity.TheatreOwner;
import com.example.demo.model.entity.User;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TheatreOwnerRepository;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/owner/create")
    public Map<String,TheatreOwner> createUser(@RequestBody TheatreOwner theatreOwner){
        return userService.createTheatreOwner(theatreOwner);
    }

    @PostMapping("/customer/create")
    public  Map<String,Customer> createUser(@RequestBody Customer customer){
        return userService.createCustomer(customer);
    }
}
