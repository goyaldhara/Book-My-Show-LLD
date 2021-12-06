package com.example.demo.model.entity;
import java.io.Serializable;
import com.example.demo.model.internal.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Movie implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String movieName;
    private Genre genre;
    private int durationInMins;
    private String language;

    @OneToMany(mappedBy="movie", cascade = CascadeType.ALL)
    @JsonIgnore
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private List<Show> shows;

    @ManyToMany
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinTable(
            name = "movie_theatre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "theatre_id"))
    private List<Theatre> theatres;

}
