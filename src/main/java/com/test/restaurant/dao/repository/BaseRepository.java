package com.test.restaurant.dao.repository;

public interface BaseRepository<D,T> {
    D findWithGraph(T id, String graphName);
}
