package com.test.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
public class Reservation {
    private int restaurantId;
    private int numOfPeople;
    private LocalDateTime reservationDateTime;
    private String customerName;
    private String phoneNumber;
    private LocalDateTime createDateTime;
}
