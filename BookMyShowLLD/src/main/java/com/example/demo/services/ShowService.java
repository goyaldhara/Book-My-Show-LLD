package com.example.demo.services;

import com.example.demo.model.entity.*;
import com.example.demo.repository.AuditoriumRepository;
import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.ShowRepository;
import com.example.demo.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;

@Service
public class ShowService {
    @Autowired
    AuditoriumRepository auditoriumRepository;
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    ShowRepository showRepository;

    public Map<String,Show> addShow(Long audiId, Long movieId){
        Optional<Auditorium> auditorium = auditoriumRepository.findById(audiId);
        Optional<Movie> movie = movieRepository.findById(movieId);
        Show show = new Show(auditorium.get().getTotalSeats());
        Map<String,Show> resp = new HashMap<String,Show>();
        if(auditorium.isPresent() && movie.isPresent()){
            show.setAuditorium(auditorium.get());
            show.setMovie(movie.get());
            showRepository.save(show);
            resp.put("showAdded",showRepository.findById(show.getShowId()).get());
            return resp;
        }
        resp.put("Audi or Movie not present show cannot be added",null);
        return resp;
    }

    public Map<String,List<Show> >displayShows(Long theatreId) {
        Optional<Theatre> theatre = theatreRepository.findById(theatreId);
        List<Show> showsList = new ArrayList<Show>();
        Map<String,List<Show> > resp = new HashMap<String,List<Show> >();
        if(theatre.isPresent()){
            for(Auditorium auditorium:theatre.get().getAuditoriums()){
                for(Show show: auditorium.getShows()){
                    showsList.add(show);
                }
            }
            resp.put("showsList",showsList);
            return resp;
        }
        resp.put("theatreDoesNotExist",null);
        return resp;
    }
}
