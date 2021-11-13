package com.test.restaurant.controller;

import com.test.restaurant.domain.Reservation;
import com.test.restaurant.domain.ReservationCancel;
import com.test.restaurant.domain.ReservationCancelResponse;
import com.test.restaurant.domain.ReservationResponse;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "reservation", tags = {"Reservation"})
public interface ReservationApi {

    @ApiOperation(value = "Returns the date string",
            notes = "This is a endpoint returning the date string for health check",
            tags={ "Reservation"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Text"),
    })
    @GetMapping(value="alive")
    String alive();

    @ApiOperation(value = "Returns the ReservationResponse object",
            notes = "This is a endpoint for customer to make a table reservation in restaurant",
            tags={ "Reservation"})
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "A JSON ReminderResponse body. It contains a reservation code."),
    })
    @PostMapping(value="create")
    @CrossOrigin
    ReservationResponse reserve(
            @ApiParam(value="A JSON Reservation body. It contains the detail of the reservation")
            @RequestBody Reservation reservation);

    @ApiOperation(value = "Returns the ReservationCancelResponse object",
            notes = "This is a endpoint for customer to cancel a reservation",
            tags={ "Reservation"})
    @ApiResponses(value = {
            @ApiResponse(code = 200,
                    message = "A JSON ReminderResponse body. It contains a message."),
    })
    @PostMapping(value="cancel")
    ReservationCancelResponse cancel(
            @ApiParam(value="A JSON ReservationCancel body. It contains the detail to cancel the reservation.")
            @RequestBody ReservationCancel reservationCancel);
}
