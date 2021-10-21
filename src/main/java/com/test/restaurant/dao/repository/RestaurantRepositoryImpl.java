package com.test.restaurant.dao.repository;

import com.test.restaurant.dao.domain.Restaurant;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

@Repository
public class RestaurantRepositoryImpl implements BaseRepository<Restaurant, Integer>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Restaurant findWithGraph(Integer id, String graphName) {
        EntityGraph entityGraph = entityManager.getEntityGraph(graphName);
        Map<String, Object> prop = new HashMap<String, Object>();
        prop.put("javax.persistence.fetchgraph", entityGraph);
        Restaurant restaurant = entityManager.find(Restaurant.class, id, prop);
        return restaurant;
    }
}
