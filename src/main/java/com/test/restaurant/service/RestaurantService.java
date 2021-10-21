package com.test.restaurant.service;

import com.test.restaurant.dao.domain.Reservation;
import com.test.restaurant.dao.domain.Restaurant;
import com.test.restaurant.domain.RestaurantInfo;

import java.util.Set;

public interface RestaurantService {
    RestaurantInfo getRestaurantInfoById(int id);
    Set<Reservation> getAllReservationById(int id);
    Restaurant getRestaurant(int id);

    Restaurant getRestaurantWithGraph(int id);
}
