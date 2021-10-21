package com.test.restaurant.dao.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NamedEntityGraph(
        name = "restaurant-with-reservation",
        attributeNodes = {@NamedAttributeNode("reservation")}
)
@Entity
@Table(name="RESTAURANT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Restaurant {
    @Id
    private int restaurantId;
    private String name;

    @OneToMany(mappedBy = "restaurant", fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<Reservation> reservation;
}
