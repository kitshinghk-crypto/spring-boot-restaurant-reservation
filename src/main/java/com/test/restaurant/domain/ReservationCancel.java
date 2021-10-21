package com.test.restaurant.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class ReservationCancel {
    private Long reservationId;
    private String phoneNumber;
}
