package com.example.demo.services;

import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Theatre;
import com.example.demo.model.entity.TheatreOwner;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.TheatreOwnerRepository;
import com.example.demo.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
    @Autowired
    TheatreOwnerRepository theatreOwnerRepository;

    @Autowired
    CustomerRepository customerRepository;

    public Map<String, TheatreOwner> createTheatreOwner(TheatreOwner theatreOwner){
        Map<String,TheatreOwner> resp = new HashMap<String,TheatreOwner>();
        List<TheatreOwner> theatreOwners = theatreOwnerRepository.findAll();
        String email = theatreOwner.getEmail();
        String username = theatreOwner.getUserName();
        for(TheatreOwner owner:theatreOwners){
            if(owner.getEmail().equals(email) || owner.getUserName().equals(username)){
                resp.put("duplicateOwner",null);
                return resp;
            }
        }
        resp.put("ownerCreated", theatreOwnerRepository.save(theatreOwner));
        return resp;
    }

    public Map<String, Customer> createCustomer(Customer customer){
        Map<String,Customer> resp = new HashMap<String,Customer>();
        List<Customer> customers = customerRepository.findAll();
        String email = customer.getEmail();
        String username = customer.getUserName();
        for(Customer exitingCustomer:customers){
            if(exitingCustomer.getEmail().equals(email) || exitingCustomer.getUserName().equals(username)){
                resp.put("duplicateCustomer",null);
                return resp;
            }
        }
        resp.put("customerCreated",customerRepository.save(customer));
        return resp;
    }
}
