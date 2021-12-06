package com.example.demo.model.entity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Theatre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String theatreName;

    @OneToMany(mappedBy="theatre", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Auditorium>auditoriums;

    @ManyToMany(mappedBy = "theatres" )
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Movie>movies;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "theatreOwner_id")
    private TheatreOwner theatreOwner;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "city_id")
    private City city;
}
