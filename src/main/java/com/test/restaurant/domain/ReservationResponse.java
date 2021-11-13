package com.test.restaurant.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@ApiModel(description = "Class representing the response body of reservation creation.")
public class ReservationResponse {
    @ApiModelProperty(value="Reservation code", name="reservationCode", dataType="java.lang.String", example="1")
    private String reservationCode;
}
