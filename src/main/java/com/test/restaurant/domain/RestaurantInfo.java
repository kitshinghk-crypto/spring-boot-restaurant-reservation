package com.test.restaurant.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel(description = "Class representing a restaurant info.")
public class RestaurantInfo {
    @ApiModelProperty(value="Restaurant ID", name="restaurantId", dataType="java.lang.Integer", example="1")
    private int restaurantId;
    @ApiModelProperty(value="Restaurant name", name="restaurantName", dataType="java.lang.String", example="Sushi bar")
    private String restaurantName;
}
