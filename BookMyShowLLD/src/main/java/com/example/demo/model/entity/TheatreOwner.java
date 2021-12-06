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
public class TheatreOwner extends User{

    @OneToMany(mappedBy = "theatreOwner", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Theatre> theatres;

}
