package com.example.demo.model.entity;

import com.example.demo.model.internal.SeatStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long seatId;

    private SeatStatus seatStatus;

    private Double price;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "show_id")
    @JsonIgnore
    private Show show;

    @ManyToOne(fetch=FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    Seat(){this.seatStatus = SeatStatus.AVAILABLE;}
}
