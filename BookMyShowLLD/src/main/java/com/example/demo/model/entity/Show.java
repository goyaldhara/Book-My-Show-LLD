package com.example.demo.model.entity;

import com.example.demo.model.internal.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
public class Show {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long showId;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "auditorium_id")
    private Auditorium auditorium;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Seat> seats;
    private Instant startTime;
    private Instant endTime;
    public Show(int size){
        this.seats = new ArrayList<Seat>(size);
    }
    public Show(){}
}
