package com.test.restaurant.service;

import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;

public interface ReservationService {
    Long createReservation(int restaurantId, int numOfPeople, LocalDateTime reservationDateTime, String customerName
            , String phoneNumber, LocalDateTime createDateTime);
    boolean cancelReservation(Long reservationId, String phoneNumber);
}
