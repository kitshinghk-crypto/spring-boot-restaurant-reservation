package com.test.restaurant.service.impl;

import com.test.restaurant.dao.domain.Reservation;
import com.test.restaurant.dao.domain.Restaurant;
import com.test.restaurant.dao.repository.RestaurantRepository;
import com.test.restaurant.domain.RestaurantInfo;
import com.test.restaurant.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Component
public class RestaurantServiceImpl implements RestaurantService {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public RestaurantInfo getRestaurantInfoById(int id) {
        Restaurant restaurant = this.restaurantRepository.getById(id);
        return new RestaurantInfo(restaurant.getRestaurantId(), restaurant.getName());
    }

    @Override
    public Set<Reservation> getAllReservationById(int id) {
        Restaurant restaurant = this.restaurantRepository.getById(id);
        return restaurant.getReservation();
    }

    @Override
    public Restaurant getRestaurant(int id){
        Optional<Restaurant> r = restaurantRepository.findById(id);
        return r.isPresent()?r.get():null;
    }

    @Override
    public Restaurant getRestaurantWithGraph(int id){
        return this.restaurantRepository.findWithGraph(id, "restaurant-with-reservation");
    }
}
