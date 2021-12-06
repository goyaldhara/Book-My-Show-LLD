package com.example.demo.services;

import com.example.demo.model.entity.Auditorium;
import com.example.demo.model.entity.Seat;
import com.example.demo.model.entity.Show;
import com.example.demo.model.entity.Theatre;
import com.example.demo.model.internal.SeatStatus;
import com.example.demo.repository.SeatRepository;
import com.example.demo.repository.ShowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    ShowRepository showRepository;
    @Autowired
    SeatRepository seatRepository;

    public void createSeatForBooking(Long showId){
        Optional<Show> show = showRepository.findById(showId);
        Auditorium audi = show.get().getAuditorium();
    }

    public List<Seat> displaySeats(Long showId) {
        Optional<Show> show = showRepository.findById(showId);
        if (show.isPresent()) {
            return show.get().getSeats();
        }
        return null;
    }

    public List<Seat> bookSeats(Long showId, List<Long> seats) {
        Optional<Show> show = showRepository.findById(showId);
        List<Seat> seatList = new ArrayList<Seat>();
        if (show.isPresent()) {
            for (Long seatId : seats) {
                Optional<Seat> seat = seatRepository.findById(seatId);
                if (seat.get().getSeatStatus().toString().equalsIgnoreCase("BOOKED")) {
                    return null;
                }
            }
            for (Long seatId : seats) {
                Optional<Seat> seat = seatRepository.findById(seatId);
                seat.get().setSeatStatus(SeatStatus.BOOKED);
                seatList.add(seat.get());
                seatRepository.save(seat.get());
            }
        }
        return seatList;
    }


}






