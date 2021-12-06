package com.example.demo.controllers;

import com.example.demo.model.entity.*;
import com.example.demo.repository.AuditoriumRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ShowRepository;
import com.example.demo.services.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
public class ShowController {
    @Autowired
    ShowService showService;

    @PostMapping("/create/show/movie/{movieId}/audi/{audiId}")
    public Map<String,Show> createShow(@PathVariable("audiId") Long audiId,
                              @PathVariable("movieId") Long movieId) {
        return showService.addShow(audiId, movieId);
    }

    @GetMapping("/show/display/theatre/{theatreId}")
    public Map<String,List<Show> >displayShows(@PathVariable("theatreId") Long theatreId) {
        return showService.displayShows(theatreId);
    }
}
