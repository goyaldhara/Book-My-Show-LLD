package com.example.demo.services;

import com.example.demo.model.entity.City;
import com.example.demo.model.entity.Movie;
import com.example.demo.model.entity.Theatre;
import com.example.demo.model.entity.TheatreOwner;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.TheatreOwnerRepository;
import com.example.demo.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;

@Service
public class TheatreService {
    @Autowired
    TheatreOwnerRepository theatreOwnerRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    TheatreRepository theatreRepository;

    public Map<String,Theatre> addTheatre(Theatre theatre, Long ownerId, String cityName){
        Map<String,Theatre> resp = new HashMap<String,Theatre>();
        Optional<TheatreOwner> owner = theatreOwnerRepository.findById(ownerId);
        Optional<City> city = cityRepository.findByCityName(cityName);
        City cityToBeAdded = city.get();
        if(!city.isPresent()) {
            cityToBeAdded = new City();
            cityToBeAdded.setCityName(cityName);
            cityRepository.save(cityToBeAdded);
        }
        if(owner.isPresent()){
            theatre.setTheatreOwner(owner.get());
            theatre.setCity(cityToBeAdded);
            theatreRepository.save(theatre);
            Theatre responseTheater =  new Theatre();

//            responseTheater.setId(theatre.getId());
//            responseTheater.setTheatreName(theatre.getTheatreName());
//            responseTheater.setCity(theatre.getCity());
            resp.put("theatreAdded",theatre);
        }
        else{
            resp.put("theatreNotAdded",null);
        }
        return resp;
    }
}