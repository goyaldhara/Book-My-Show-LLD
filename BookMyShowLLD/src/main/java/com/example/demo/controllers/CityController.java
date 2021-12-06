package com.example.demo.controllers;
import com.example.demo.model.entity.City;
import com.example.demo.model.entity.Movie;
import com.example.demo.repository.CityRepository;
import com.example.demo.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;


@RestController
public class CityController {
    @Autowired
    CityService cityService;

    @PostMapping("/city/create")
    public Map<String,City> createCity(@RequestBody City city){
        return cityService.createCity(city);
    }

    @GetMapping("/city/display")
    public Map<String,List<City> > dislplayAllCitites(){
        return cityService.getAllCitiesWithTheatres();
    }

    @GetMapping("/movie/display/city/{cityName}")
    public Map<String,HashSet<Movie> > displayMoviesInCity(@PathVariable ("cityName") String cityName){
        return cityService.getAllMoviesInCity(cityName);
    }
}
