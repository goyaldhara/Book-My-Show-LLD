package com.example.demo.controllers;

import com.example.demo.model.entity.Auditorium;
import com.example.demo.model.entity.City;
import com.example.demo.model.entity.Theatre;
import com.example.demo.model.entity.TheatreOwner;
import com.example.demo.repository.AuditoriumRepository;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.TheatreOwnerRepository;
import com.example.demo.repository.TheatreRepository;
import com.example.demo.services.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Map;

@RestController
public class TheatreController {

   @Autowired
    TheatreService theatreService;

    @PostMapping("/theatre/create/owner/{ownerId}/city/{cityName}")
    public Map<String,Theatre> createTheatre(@RequestBody (required = true) Theatre theatre,
                                 @PathVariable (value = "ownerId", required = true) Long ownerId,
                                 @PathVariable (value = "cityName", required = true) String cityName){
        return theatreService.addTheatre(theatre,ownerId,cityName);
    }
}
