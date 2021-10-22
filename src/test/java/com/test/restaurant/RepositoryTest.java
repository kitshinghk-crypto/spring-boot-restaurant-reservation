package com.test.restaurant;

import com.test.restaurant.dao.domain.Reservation;
import com.test.restaurant.dao.domain.Restaurant;
import com.test.restaurant.dao.repository.ReservationRepository;
import com.test.restaurant.dao.repository.RestaurantRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryTest {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void testCreateReservation(){
        Reservation r = new Reservation(2, LocalDateTime.now(), "Tester1",
                "123", LocalDateTime.now());
        Reservation save = this.reservationRepository.save(r);
        assertTrue(save.getReservationId()!=null);
        Optional<Reservation> get = this.reservationRepository.findById(save.getReservationId());
        assertTrue(get.isPresent());
    }

    @Test
    public void testDeleteReservation(){
        Reservation r = new Reservation(2, LocalDateTime.now(), "Tester1",
                "123", LocalDateTime.now());
        Reservation save = this.reservationRepository.save(r);
        assertTrue(save.getReservationId()!=null);
        this.reservationRepository.delete(save);
        Optional<Reservation> get = this.reservationRepository.findById(save.getReservationId());
        assertTrue(get.isEmpty());
    }

    @Test
    public void testGetRestaurant(){
        Restaurant r = new Restaurant();
        r.setRestaurantId(5);
        r.setName("test restaurant");
        this.restaurantRepository.save(r);
        Optional<Restaurant> get = this.restaurantRepository.findById(5);
        assertTrue(get.isPresent());
        assertTrue(get.get().getRestaurantId()==5);
    }
}
