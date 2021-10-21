package com.test.restaurant.controller;

import com.test.restaurant.domain.Reservation;
import com.test.restaurant.domain.ReservationCancel;
import com.test.restaurant.domain.ReservationCancelResponse;
import com.test.restaurant.domain.ReservationResponse;
import com.test.restaurant.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping(value="reservation/")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping(value="alive")
    public String alive(){
        return LocalDateTime.now().toString();
    }

    @PostMapping(value="create/")
    public ReservationResponse reserve(@RequestBody Reservation reservation){
        Long reservationId = this.reservationService.createReservation(reservation.getRestaurantId(),
                reservation.getNumOfPeople(), reservation.getReservationDateTime(), reservation.getCustomerName(),
        reservation.getPhoneNumber(), reservation.getCreateDateTime());
        return new ReservationResponse(reservationId.toString());
    }

    @PostMapping(value="cancel/")
    public ReservationCancelResponse cancel(@RequestBody ReservationCancel reservationCancel){
        String msg = this.reservationService.cancelReservation(reservationCancel.getReservationId()
                , reservationCancel.getPhoneNumber())?"success":"fail";
        return new ReservationCancelResponse(msg);
    }
}
