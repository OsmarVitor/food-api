package com.algaworks.foodapi.domain.service.impl;

import com.algaworks.foodapi.domain.exception.EntityInUseException;
import com.algaworks.foodapi.domain.exception.EntityNotFoundException;
import com.algaworks.foodapi.domain.model.State;
import com.algaworks.foodapi.domain.repository.StateRepository;
import com.algaworks.foodapi.domain.service.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StateServiceImpl implements StateService {

    @Autowired
    private StateRepository stateRepository;

    @Override
    public Page<State> list(int page, int size) {
        return stateRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public State find(long id) {
        return findState(id);
    }

    @Override
    public State create(State state) {
        return stateRepository.save(state);
    }

    @Override
    public State update(long id, State stateUpdated) {
        State stateToUpdate = findState(id);
        BeanUtils.copyProperties(stateUpdated, stateToUpdate, "id");
        return stateRepository.save(stateUpdated);
    }

    @Override
    public void delete(Long stateId) {
        State stateToDelete = findState(stateId);

        try {
            stateRepository.delete(stateToDelete);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("City with id %d cannot be deleted because it is in use", stateId));
        }
    }

    private State findState(long stateId){
        return stateRepository.findById(stateId).orElseThrow(() -> new EntityNotFoundException(stateId));
    }


}
