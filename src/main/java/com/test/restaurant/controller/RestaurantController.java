package com.test.restaurant.controller;

import com.test.restaurant.dao.domain.Reservation;
import com.test.restaurant.dao.domain.Restaurant;
import com.test.restaurant.domain.RestaurantInfo;
import com.test.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path="restaurant/")
public class RestaurantController implements RestaurantApi {
    @Autowired
    private RestaurantService restaurantService;

    @Override
    @GetMapping(value="getInfo/{restaurantId}")
    public RestaurantInfo getRestaurant(@PathVariable int restaurantId){
        return this.restaurantService.getRestaurantInfoById(restaurantId);
    }

    @Override
    @GetMapping(value="getInfo/")
    @CrossOrigin
    public List<RestaurantInfo> getAllRestaurant(){
        return this.restaurantService.getAllRestaurantInfo();
    }

    @Override
    @GetMapping(value="getAllReservation/{restaurantId}")
    @CrossOrigin
    public Set<Reservation> getAllReservation(@PathVariable int restaurantId){
        return this.restaurantService.getAllReservationById(restaurantId);
    }

    @Override
    @GetMapping(value="getAllReservationAndInfo/{restaurantId}")
    @CrossOrigin
    public Restaurant getAllRestaurantAndInfo(@PathVariable int restaurantId){
        return this.restaurantService.getRestaurant(restaurantId);
    }
}
