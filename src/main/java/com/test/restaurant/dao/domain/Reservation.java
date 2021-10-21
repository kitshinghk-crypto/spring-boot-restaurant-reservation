package com.test.restaurant.dao.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="RESERVATION")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @SequenceGenerator(name="reservation_sequence", sequenceName = "reservation_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_sequence")
    private Long reservationId;
    private int numOfPeople;
    private LocalDateTime reservationDatetime;
    private String customerName;
    private String phoneNumber;
    private LocalDateTime createDatetime;

    @ManyToOne
    @JoinColumn(name="restaurantId")
    @JsonBackReference
    private Restaurant restaurant;

    public Reservation(int numOfPeople, LocalDateTime reservationDateTime, String customerName,
                       String phoneNumber, LocalDateTime createDateTime){
        //this.restaurantId = restaurantId;
        this.numOfPeople = numOfPeople;
        this.reservationDatetime = reservationDateTime;
        this.customerName = customerName;
        this.phoneNumber = phoneNumber;
        this.createDatetime = createDateTime;
    }
}
