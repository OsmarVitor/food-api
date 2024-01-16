package com.algaworks.foodapi.domain.service;

import com.algaworks.foodapi.domain.model.Restaurant;

public interface RestaurantService {
    Restaurant createRestaurant(Restaurant restaurant);

    void deleteRestaurant(Long restaurantId);
}
