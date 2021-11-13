package com.test.restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.test.restaurant.controller.ReservationController;
import com.test.restaurant.controller.RestaurantController;
import com.test.restaurant.dao.domain.Restaurant;
import com.test.restaurant.domain.Reservation;
import com.test.restaurant.domain.ReservationCancel;
import com.test.restaurant.domain.RestaurantInfo;
import com.test.restaurant.service.ReservationService;
import com.test.restaurant.service.RestaurantService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.hamcrest.CoreMatchers;

import javax.print.attribute.standard.Media;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {ReservationController.class, RestaurantController.class})
public class ControllerTest {

    private static final String ALIVE_PATH = "/reservation/alive";
    private static final String CREATE_RESERVATION_PATH = "/reservation/create";
    private static final String CANCEL_RESERVATION_PATH = "/reservation/cancel";
    private static final String GET_RESTAURANT_INFO_PATH = "/restaurant/getInfo/%d";
    private static final String GET_RESTAURANT_PATH = "/restaurant/getAllReservationAndInfo/%d";
    private static final String GET_RESERVATION_PATH = "/restaurant/getAllReservation/%d";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;
    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void testAlive() throws Exception{
        mockMvc
                .perform(MockMvcRequestBuilders.get(ALIVE_PATH))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateReservation() throws Exception{
        LocalDateTime now = LocalDateTime.now();
        Mockito.when(reservationService.createReservation(1, 2,now,"Test"
        ,"123", now)).thenReturn(1L);
        Reservation reservation = new Reservation(1,2, now, "Test",
                "123", now);

        Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>(){
            @Override
            public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
                return new JsonPrimitive(localDateTime.toString());
            }
        }).create();

        ObjectMapper om = new ObjectMapper();
        mockMvc.perform(MockMvcRequestBuilders.post(CREATE_RESERVATION_PATH).contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(reservation)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.reservationCode",CoreMatchers.is("1")));
    }

    @Test
    public void testCancelReservation() throws Exception{
        Mockito.when(reservationService.cancelReservation(1L, "123")).thenReturn(true);
        Gson gson = new Gson();
        ReservationCancel c = new ReservationCancel(1L, "123");
        mockMvc.perform(MockMvcRequestBuilders.post(CANCEL_RESERVATION_PATH).contentType(MediaType.APPLICATION_JSON)
                .content(gson.toJson(c)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.msg", CoreMatchers.is("success")));
    }

    @Test
    public void testGetRestaurantInfo() throws Exception{
        RestaurantInfo info = new RestaurantInfo(1, "test");
        Mockito.when(this.restaurantService.getRestaurantInfoById(1)).thenReturn(info);
        Gson gson = new Gson();
        mockMvc.perform(MockMvcRequestBuilders.get(String.format(GET_RESTAURANT_INFO_PATH, Integer.valueOf(1))))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(gson.toJson(info)));
    }

    @Test
    public void testGetRestaurantReservation() throws Exception{
        LocalDateTime now = LocalDateTime.now();
        com.test.restaurant.dao.domain.Reservation reservation = new com.test.restaurant.dao.domain.Reservation(
                1L, 1, now, "test", "123",  now, null);
        Mockito.when(restaurantService.getAllReservationById(1)).thenReturn(Set.of(reservation));
        Gson gson = new Gson();
        mockMvc.perform(MockMvcRequestBuilders.get(String.format(GET_RESERVATION_PATH, Integer.valueOf(1))))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(Matchers.equalTo(1))));
                //.andExpect(content().json(gson.toJson(Set.of(reservation))));
    }

    @Test
    public void testGetRestaurant() throws Exception{
        LocalDateTime now = LocalDateTime.now();
        com.test.restaurant.dao.domain.Reservation reservation = new com.test.restaurant.dao.domain.Reservation(
                1L, 1, now, "test", "123",  now, null);
        Restaurant restaurant = new Restaurant(1, "test", Set.of(reservation));
        Mockito.when(restaurantService.getRestaurant(1)).thenReturn(restaurant);
        mockMvc.perform(MockMvcRequestBuilders.get(String.format(GET_RESTAURANT_PATH, Integer.valueOf(1))))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.restaurantId", Matchers.equalTo(Integer.valueOf(1))))
                .andExpect(jsonPath("$.name", Matchers.equalTo("test")))
                .andExpect(jsonPath("$.reservation", Matchers.hasSize(Matchers.equalTo(1))));
    }
}
