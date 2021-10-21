package com.test.restaurant.service.impl;

import com.test.restaurant.dao.domain.Reservation;
import com.test.restaurant.dao.domain.Restaurant;
import com.test.restaurant.dao.repository.ReservationRepository;
import com.test.restaurant.dao.repository.RestaurantRepository;
import com.test.restaurant.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Long createReservation(int restaurantId, int numOfPeople, LocalDateTime reservationDateTime,
                                    String customerName, String phoneNumber, LocalDateTime createDateTime) {
        Reservation r = new Reservation(numOfPeople, reservationDateTime, customerName, phoneNumber
                                        ,createDateTime);
        Restaurant restaurant = this.restaurantRepository.getById(restaurantId);
        r.setRestaurant(restaurant);
        Reservation save = this.reservationRepository.save(r);
        return save.getReservationId();
    }

    @Override
    public boolean cancelReservation(Long reservationId, String phoneNumber) {
        Optional<Reservation> r = this.reservationRepository.findById(reservationId);
        if(r.isPresent()){
            if(r.get().getPhoneNumber().equals(phoneNumber)){
                this.reservationRepository.delete(r.get());
                return true;
            }else{
                return false;
            }
        }else {
            return false;
        }
    }
}
