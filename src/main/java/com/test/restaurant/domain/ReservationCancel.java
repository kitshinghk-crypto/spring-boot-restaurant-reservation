package com.test.restaurant.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@ApiModel(description = "Class representing the request body of reservation cancellation.")
public class ReservationCancel {
    @ApiModelProperty(value="Reservatoin ID", name="reservationId", dataType="java.lang.Long", example="1")
    private Long reservationId;
    @ApiModelProperty(value="Phone number of the person who made the reservation",
            name="phoneNumber", dataType="java.lang.String", example="2322449857")
    private String phoneNumber;
}
