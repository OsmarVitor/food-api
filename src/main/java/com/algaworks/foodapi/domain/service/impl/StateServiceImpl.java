package com.algaworks.foodapi.domain.service.impl;

import com.algaworks.foodapi.domain.exception.EntityInUseException;
import com.algaworks.foodapi.domain.exception.EntityNotFoundException;
import com.algaworks.foodapi.domain.model.State;
import com.algaworks.foodapi.domain.repository.StateRepository;
import com.algaworks.foodapi.domain.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    @Override
    public Page<State> listStates(int page, int size) {
        return stateRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public State findState(long id) {
        return findStateById(id);
    }

    @Override
    public State createState(State state) {
        return stateRepository.save(state);
    }

    @Override
    public State updateState(long id, State stateUpdated) {
        State stateToUpdate = findStateById(id);
        BeanUtils.copyProperties(stateUpdated, stateToUpdate, "id");
        return stateRepository.save(stateToUpdate);
    }

    @Override
    public void deleteState(Long stateId) {
        State stateToDelete = findStateById(stateId);

        try {
            stateRepository.delete(stateToDelete);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("State with id %d cannot be deleted because it is in use", stateId));
        }
    }

    private State findStateById(long stateId){
        return stateRepository.findById(stateId).orElseThrow(() -> new EntityNotFoundException(stateId));
    }


}
