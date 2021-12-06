package com.example.demo.controllers;

import com.example.demo.model.entity.Auditorium;
import com.example.demo.model.entity.Customer;
import com.example.demo.model.entity.Theatre;
import com.example.demo.repository.AuditoriumRepository;
import com.example.demo.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;
import java.util.Map;
@RestController
public class AuditoriumController {

    @Autowired
    AuditoriumRepository auditoriumRepository;
    @Autowired
    TheatreRepository theatreRepository;

    @PostMapping("/auditorium/create/theatre/{theatreId}")
    public Map<String,Auditorium> createAudi(@RequestBody Auditorium auditorium,
                                 @PathVariable ("theatreId") Long theatreId){
        Map<String,Auditorium> resp = new HashMap<String, Auditorium>();
        Optional<Theatre> theatre = theatreRepository.findById(theatreId);
        if(theatre.isPresent()){
            theatre.get().getAuditoriums().add(auditorium);

            auditorium.setTheatre(theatre.get());
            auditorium.setTheatreName(theatre.get().getTheatreName());
            auditorium.setTheatreIdx(theatre.get().getId());
            auditoriumRepository.save(auditorium);

            theatre.get().getAuditoriums().add(auditorium);
            theatreRepository.save(theatre.get());

            resp.put("audiAdded", auditoriumRepository.findById(auditorium.getId()).get());
        }
        else
            resp.put("theatreDoesNotExist",null);
        return resp;
    }
}
