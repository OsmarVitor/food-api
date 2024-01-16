package com.algaworks.foodapi.domain.service.impl;

import com.algaworks.foodapi.domain.exception.EntityInUseException;
import com.algaworks.foodapi.domain.exception.EntityNotFoundException;
import com.algaworks.foodapi.domain.model.Restaurant;
import com.algaworks.foodapi.domain.repository.RestaurantRepository;
import com.algaworks.foodapi.domain.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) {
        Restaurant restaurantToDelete = findById(restaurantId);

        try {
            restaurantRepository.delete(restaurantToDelete);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Restaurant with id %d cannot be deleted because it is in use", restaurantId));
        }

    }

    private Restaurant findById(Long restaurantId){
        return restaurantRepository.findById(restaurantId).orElseThrow(() -> new EntityNotFoundException(restaurantId));
    }
}
