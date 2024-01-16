package com.algaworks.foodapi.domain.service;

import com.algaworks.foodapi.domain.model.Kitchen;

public interface KitchenService {

    Kitchen createKitchen(Kitchen kitchen);

    void deleteKitchen(Long kitchenId);
}
