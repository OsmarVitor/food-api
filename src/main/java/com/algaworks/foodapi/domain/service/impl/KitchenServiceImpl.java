package com.algaworks.foodapi.domain.service.impl;

import com.algaworks.foodapi.domain.exception.EntityInUseException;
import com.algaworks.foodapi.domain.exception.EntityNotFoundException;
import com.algaworks.foodapi.domain.model.Kitchen;
import com.algaworks.foodapi.domain.repository.KitchenRepository;
import com.algaworks.foodapi.domain.service.KitchenService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KitchenServiceImpl implements KitchenService {

    private final KitchenRepository kitchenRepository;

    @Override
    public Kitchen createKitchen(Kitchen kitchen) {
        return kitchenRepository.save(kitchen);
    }

    @Override
    public void deleteKitchen(Long kitchenId) {
        Kitchen kitchenToDelete = findById(kitchenId);
        try {
            kitchenRepository.delete(kitchenToDelete);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("Kitchen with id %d cannot be deleted because it is in use", kitchenId));
        }
    }

    private Kitchen findById(Long kitchenId){
        return kitchenRepository.findById(kitchenId).orElseThrow(() -> new EntityNotFoundException(kitchenId));
    }
}
