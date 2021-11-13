package com.test.restaurant.service;

import com.test.restaurant.dao.domain.Reservation;
import com.test.restaurant.dao.domain.Restaurant;
import com.test.restaurant.domain.RestaurantInfo;

import java.util.List;
import java.util.Set;

public interface RestaurantService {
    RestaurantInfo getRestaurantInfoById(int id);

    List<RestaurantInfo> getAllRestaurantInfo();

    Set<Reservation> getAllReservationById(int id);
    Restaurant getRestaurant(int id);
}
