package com.example.demo.services;

import com.example.demo.model.entity.City;
import com.example.demo.model.entity.Movie;
import com.example.demo.model.entity.Theatre;
import com.example.demo.model.entity.TheatreOwner;
import com.example.demo.repository.CityRepository;
import com.example.demo.repository.TheatreOwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.ArrayList;

@Service
public class CityService {

    @Autowired
    TheatreOwnerRepository theatreOwnerRepository;
    @Autowired
    CityRepository cityRepository;

    public Map<String,City> createCity(City currentCity){
        Map<String, City> resp = new HashMap<String,City>();
        List<City> cities = cityRepository.findAll();
        String cityName = currentCity.getCityName();
        for(City city : cities){
            if(city.getCityName().equals(cityName)){
                resp.put("duplicateCity",null);
                return resp;
            }
        }
        resp.put("cityAdded",cityRepository.save(currentCity));
        return resp;
    }

    public Map<String,List<City> > getAllCitiesWithTheatres() {
        Map<String, List<City> > response = new HashMap<String, List<City> >();
        List<City>cities = cityRepository.findAll();
        List<City> result = new ArrayList<City>();
        for(City city: cities){
            if(city.getTheatres().size()!=0){
                result.add(city);
            }
        }
        if(result.size()!=0)
            response.put("listOfCities",result);
        else
            response.put("noCitiesWithTheatresExist",null);
        return response;
    }

    public Map<String,HashSet<Movie> > getAllMoviesInCity(String cityName){
        HashSet<Movie> result = new HashSet<Movie>();
        Map<String,HashSet<Movie> > resp = new HashMap<String,HashSet<Movie>>();
        Optional<City> city1 = cityRepository.findByCityName(cityName);
        Optional<City> city = cityRepository.findById(city1.get().getId());
        if(city.isPresent()){
            List<Theatre> theatres = city.get().getTheatres();
            for(Theatre theatre: theatres){
                for(Movie movie: theatre.getMovies()){
                    result.add(movie);
                }
            }
            if(result.size()==0){
                resp.put("noMoviesExistIn "+cityName,result);
                return resp;
            }
            resp.put(cityName,result);
            return resp;
        }
        resp.put("City does not exist",null);
        return resp;
    }
}
