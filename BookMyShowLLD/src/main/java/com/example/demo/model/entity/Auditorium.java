package com.example.demo.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Data
public class Auditorium {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "theatre_id")
    @JsonIgnore
    private Theatre theatre;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "auditorium_id")
    @JsonIgnore
    private List<Show> shows;

    private String audiName;

    private int totalSeats;

    private String theatreName;

    private Long theatreIdx;
}
