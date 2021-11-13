package com.test.restaurant.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@ApiModel(description = "Class representing the response body of reservation cancellation.")
public class ReservationCancelResponse {
    @ApiModelProperty(value="Response message of reservation cancellation.",
            name="msg", dataType="java.lang.String", example="success")
    private String msg;
}
