package com.test.restaurant.dao.repository;

import com.test.restaurant.dao.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> , BaseRepository<Restaurant, Integer>{
}
