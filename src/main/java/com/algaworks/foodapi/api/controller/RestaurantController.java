package com.algaworks.foodapi.api.controller;

import com.algaworks.foodapi.domain.model.City;
import com.algaworks.foodapi.domain.model.Restaurant;
import com.algaworks.foodapi.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        Restaurant restaurantSaved = restaurantService.createRestaurant(restaurant);
        return ResponseEntity.status(HttpStatus.CREATED).body(restaurantSaved);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<City> delete(@PathVariable Long restaurant) {
        restaurantService.deleteRestaurant(restaurant);
        return ResponseEntity.noContent().build();
    }
}
