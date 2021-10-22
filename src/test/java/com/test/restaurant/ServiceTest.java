package com.test.restaurant;

import com.test.restaurant.dao.domain.Reservation;
import com.test.restaurant.dao.domain.Restaurant;
import com.test.restaurant.dao.repository.ReservationRepository;
import com.test.restaurant.dao.repository.RestaurantRepository;
import com.test.restaurant.domain.RestaurantInfo;
import com.test.restaurant.service.ReservationService;
import com.test.restaurant.service.RestaurantService;
import com.test.restaurant.service.impl.ReservationServiceImpl;
import com.test.restaurant.service.impl.RestaurantServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
public class ServiceTest {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private ReservationService reservationService;

    @TestConfiguration
    static class configuration{
        private RestaurantRepository restaurantRepository;
        private ReservationRepository reservationRepository;

        @Bean
        public RestaurantRepository mockRestaurantRepository(){
            restaurantRepository = BDDMockito.mock(RestaurantRepository.class);
            Restaurant restaurant = new Restaurant();
            restaurant.setRestaurantId(1);
            restaurant.setName("Test Restaurant");
            Reservation reservation1 = new Reservation(2, LocalDateTime.now(), "Tester1",
                    "123456", LocalDateTime.now());
            reservation1.setReservationId(1L);
            Reservation reservation2 = new Reservation(3, LocalDateTime.now(), "Tester2",
                    "987654", LocalDateTime.now());
            reservation2.setReservationId(2L);
            HashSet<Reservation> reservations = new HashSet<Reservation>();
            reservations.add(reservation1);
            reservations.add(reservation2);
            restaurant.setReservation(reservations);
            BDDMockito.given(restaurantRepository.getById(1)).willReturn(restaurant);
            BDDMockito.given(restaurantRepository.findWithGraph(1, "restaurant-with-reservation"))
                    .willReturn(restaurant);

            return restaurantRepository;
        }

        @Bean
        public ReservationRepository mockReservationRepository(){
            reservationRepository = BDDMockito.mock(ReservationRepository.class);
            Reservation reservation1 = new Reservation(2, LocalDateTime.now(), "Tester1",
                    "123456", LocalDateTime.now());
            reservation1.setReservationId(1L);
            Reservation reservation2 = new Reservation(3, LocalDateTime.now(), "Tester2",
                    "987654", LocalDateTime.now());
            reservation2.setReservationId(2L);
            BDDMockito.given(reservationRepository.findById(1L)).willReturn(Optional.of(reservation1));
            BDDMockito.given(reservationRepository.findById(2L)).willReturn(Optional.of(reservation2));
            BDDMockito.when(reservationRepository.save(BDDMockito.any(Reservation.class))).thenReturn(reservation1);
            return reservationRepository;
        }

        @Bean
        public RestaurantService restaurantService(){
            RestaurantService restaurantService = new RestaurantServiceImpl(this.restaurantRepository);
            return restaurantService;
        }

        @Bean
        public ReservationService reservationService(){
            ReservationService reservationService = new ReservationServiceImpl(this.reservationRepository,
                    this.restaurantRepository);
            return reservationService;
        }
    }

    @Test
    public void testCreateReservation(){
        Long reservationId = this.reservationService.createReservation(1, 2, LocalDateTime.now(),
                "Tester1", "123456", LocalDateTime.now());
        assertTrue(reservationId==1L);
    }

    @Test
    public void testCancelReservation(){
        assertTrue(this.reservationService.cancelReservation(1L, "123456"));
        assertFalse(this.reservationService.cancelReservation(3L, "123456"));
    }

    @Test
    public void testGetRestaurantInfo(){
        RestaurantInfo info = this.restaurantService.getRestaurantInfoById(1);
        assertTrue(info.getRestaurantId()==1);
        assertTrue(info.getRestaurantName().equals("Test Restaurant"));
    }

    @Test
    public void testGetAllReservation(){
        Set<Reservation> reservations = this.restaurantService.getAllReservationById(1);
        assertTrue(reservations!=null);
        assertTrue(reservations.size()==2);
    }

    @Test
    public void testGetRestaurant(){
        Restaurant restaurant = this.restaurantService.getRestaurant(1);
        assertTrue(restaurant.getRestaurantId()==1);
        assertTrue(restaurant.getName().equals("Test Restaurant"));
        assertTrue(restaurant.getReservation()!=null);
        assertTrue(restaurant.getReservation().size()==2);
    }
}
