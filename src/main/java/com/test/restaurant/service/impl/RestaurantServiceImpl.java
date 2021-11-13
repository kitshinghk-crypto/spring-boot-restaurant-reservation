package com.test.restaurant.service.impl;

import com.test.restaurant.dao.domain.Reservation;
import com.test.restaurant.dao.domain.Restaurant;
import com.test.restaurant.dao.repository.RestaurantRepository;
import com.test.restaurant.domain.RestaurantInfo;
import com.test.restaurant.service.RestaurantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
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
    public List<RestaurantInfo> getAllRestaurantInfo(){
        List<Restaurant> allRestaurants  = this.restaurantRepository.findAll();
        return allRestaurants.stream()
                .map(r-> new RestaurantInfo(r.getRestaurantId(), r.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Set<Reservation> getAllReservationById(int id) {
        Restaurant restaurant = this.restaurantRepository.getById(id);
        return restaurant.getReservation();
    }

    @Override
    public Restaurant getRestaurant(int id){
        return this.restaurantRepository.findWithGraph(id, "restaurant-with-reservation");
    }
}
