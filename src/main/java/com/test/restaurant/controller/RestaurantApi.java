package com.test.restaurant.controller;

import com.test.restaurant.dao.domain.Reservation;
import com.test.restaurant.dao.domain.Restaurant;
import com.test.restaurant.domain.RestaurantInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Api(value = "restaurant", tags = {"Restaurant"})
public interface RestaurantApi {

    @ApiOperation(value = "Returns the information of the restaurant",
            notes = "This is a endpoint returning the information of the restaurant",
            tags={ "Restaurant"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A JSON RestaurantInfo body."),
    })
    @GetMapping(value="getInfo/{restaurantId}")
    RestaurantInfo getRestaurant(@PathVariable int restaurantId);

    @ApiOperation(value = "Returns the information of all restaurants",
            notes = "This is a endpoint returning the information of all restaurants",
            tags={ "Restaurant"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A JSON RestaurantInfo body."),
    })
    @GetMapping(value="getInfo/")
    @CrossOrigin
    List<RestaurantInfo> getAllRestaurant();

    @ApiOperation(value = "Returns the information of all reservation of an specified restaurant",
            notes = "This is a endpoint returning the information of all reservation of an specified restaurant",
            tags={ "Restaurant"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A JSON of list of Reservation."),
    })
    @GetMapping(value="getAllReservation/{restaurantId}")
    @CrossOrigin
    Set<Reservation> getAllReservation(@PathVariable int restaurantId);

    @ApiOperation(value = "Returns the information and all reservation of an specified restaurant",
            notes = "This is a endpoint returning the information and all reservation of an specified restaurant",
            tags={ "Restaurant"})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "A JSON of Restaurant body."),
    })
    @GetMapping(value="getAllReservationAndInfo/{restaurantId}")
    @CrossOrigin
    Restaurant getAllRestaurantAndInfo(@PathVariable int restaurantId);
}
