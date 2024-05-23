package com.att.tdp.bisbis10.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double averageRating;

    private Boolean isKosher;

    @ElementCollection
    private List<String> cuisines;

    @OneToMany(mappedBy = "restaurant")
    private List<Dish> dishes;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Order> orders;

    @JsonIgnore
    @OneToMany(mappedBy = "restaurant")
    private List<Rating> ratings;

}