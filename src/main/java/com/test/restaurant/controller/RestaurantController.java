package com.test.restaurant.controller;

import com.test.restaurant.dao.domain.Reservation;
import com.test.restaurant.dao.domain.Restaurant;
import com.test.restaurant.domain.RestaurantInfo;
import com.test.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="restaurant/")
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(value="getInfo/{restaurantId}")
    public RestaurantInfo getRestaurant(@PathVariable int restaurantId){
        return this.restaurantService.getRestaurantInfoById(restaurantId);
    }

    @GetMapping(value="getAllReservation/{restaurantId}")
    public Set<Reservation> getAllReservation(@PathVariable int restaurantId){
        return this.restaurantService.getAllReservationById(restaurantId);
    }

    @GetMapping(value="getAllReservationAndInfo/{restaurantId}")
    public Restaurant getAllRestaurantAndInfo(@PathVariable int restaurantId){
        return this.restaurantService.getRestaurant(restaurantId);
    }

    @GetMapping(value="getAllReservationAndInfoWithGraph/{restaurantId}")
    public Restaurant getAllRestaurantAndInfoWithGraph(@PathVariable int restaurantId){
        return this.restaurantService.getRestaurantWithGraph(restaurantId);
    }
}
