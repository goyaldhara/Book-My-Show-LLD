package com.example.demo.controllers;
import java.io.Serializable;

import com.example.demo.model.entity.Movie;
import com.example.demo.model.entity.Show;
import com.example.demo.model.entity.Theatre;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.TheatreRepository;
import com.example.demo.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
public class MovieController{

    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    MovieService movieService;

    @PostMapping("/create/movie/theatre/{theatreId}")
    public Map<String,Movie> createMovie(@RequestBody Movie movie,
                             @PathVariable("theatreId") Long theatreId) {
        return movieService.createMovie(movie,theatreId);
    }
}
