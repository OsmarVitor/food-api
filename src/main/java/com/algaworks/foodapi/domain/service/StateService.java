package com.algaworks.foodapi.domain.service;

import com.algaworks.foodapi.domain.model.State;
import org.springframework.data.domain.Page;

public interface StateService {

    State create(State state);

    State find(long id);

    State update(long id, State state);

    void delete(Long stateId);

    Page<State> list(int page, int size);
}
