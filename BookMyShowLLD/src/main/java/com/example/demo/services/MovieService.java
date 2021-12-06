package com.example.demo.services;

import com.example.demo.model.entity.Movie;
import com.example.demo.model.entity.Theatre;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieService implements Serializable {

    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    MovieRepository movieRepository;

    public Map<String,Movie> createMovie(Movie movie, Long theatreId){
        Optional<Theatre> theatre = theatreRepository.findById(theatreId);
        Map<String,Movie> resp = new HashMap<String,Movie>();
        if(theatre.isPresent()){
            theatre.get().getMovies().add(movie);
            theatreRepository.save(theatre.get());
            movie.getTheatres().add(theatre.get());
            try{
                movieRepository.save(movie);
                resp.put("newMovieAdded",movieRepository.findById(movie.getId()).get());
                return resp;
            }
            catch (Exception e){
                resp.put(e.getMessage(),null);
                System.out.println(e);
                return resp;
            }
        }
        resp.put("theatreDoesNotExist",null);
        return resp;
    }
}
