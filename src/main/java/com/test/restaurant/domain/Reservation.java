package com.test.restaurant.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Setter
@Getter
@ApiModel(description = "Class representing a reservation.")
public class Reservation {
    @ApiModelProperty(value="Restaurant ID", name="restaurantId", dataType="java.lang.Integer", example="1")
    private int restaurantId;
    @ApiModelProperty(value="Number of the people for the reservation",
            name="numOfPeople", dataType="java.lang.Integer", example="3")
    private int numOfPeople;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value="Date Time for the reservation",
            name="reservationDateTime", dataType="java.time.String", example="2021-10-21T21:47:43.885")
    private LocalDateTime reservationDateTime;
    @ApiModelProperty(value="Name of the person who made the reservation",
            name="customerName", dataType="java.lang.String", example="John Doe")
    private String customerName;
    @ApiModelProperty(value="Phone number of the person who made the reservation",
            name="phoneNumber", dataType="java.lang.String", example="2322449857")
    private String phoneNumber;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @ApiModelProperty(value="Create date time of the reservation",
            name="createDateTime", dataType="java.time.String", example="2021-10-21T21:47:43.885")
    private LocalDateTime createDateTime;
}
