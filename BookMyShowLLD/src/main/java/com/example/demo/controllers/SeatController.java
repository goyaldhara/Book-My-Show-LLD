package com.example.demo.controllers;

import com.example.demo.model.entity.Seat;
import com.example.demo.model.entity.Show;
import com.example.demo.repository.SeatRepository;
import com.example.demo.repository.ShowRepository;
import com.example.demo.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SeatController {

    @Autowired
    SeatRepository seatRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    BookingService bookingService;

    @PostMapping("/create/seat/show/{showId}")
    public Seat createSeat(@RequestBody Seat seat,
                           @PathVariable("showId") Long showId) {
        Optional<Show> show = showRepository.findById(showId);
        if(show.isPresent()){
            seat.setShow(show.get());
            return seatRepository.save(seat);
        }
        return null;
    }

    @PostMapping("/book/seat")
    public List<Seat> createSeat(@RequestBody List<Long> seats,
                                 @PathVariable("showId") Long showId) {
        return bookingService.bookSeats(showId,seats);
    }

    @GetMapping("/display/seat")
    public List<Seat> displaySeat(@PathVariable("showId") Long showId) {
        return bookingService.displaySeats(showId);
    }
}
