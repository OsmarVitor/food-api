package com.algaworks.foodapi.domain.service;

import com.algaworks.foodapi.domain.model.State;
import org.springframework.data.domain.Page;

public interface StateService {

    State createState(State state);

    State findState(long id);

    State updateState(long id, State state);

    void deleteState(Long stateId);

    Page<State> listStates(int page, int size);
}
