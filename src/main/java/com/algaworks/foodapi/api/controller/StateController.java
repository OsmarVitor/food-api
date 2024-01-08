package com.algaworks.foodapi.api.controller;

import com.algaworks.foodapi.domain.model.State;
import com.algaworks.foodapi.domain.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/states")
@RequiredArgsConstructor
public class StateController {

    private final StateService stateService;

    @PostMapping
    public ResponseEntity<State> createState(@RequestBody State state) {
        State stateSaved = stateService.createState(state);
        return ResponseEntity.status(HttpStatus.CREATED).body(stateSaved);
    }

    @GetMapping
    public ResponseEntity<Page<State>> listState(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(stateService.listStates(page, size));
    }

    @GetMapping("/{stateId}")
    public ResponseEntity<State> findState(@PathVariable Long stateId) {
        State state = stateService.findState(stateId);
        return ResponseEntity.ok(state);
    }

    @PutMapping("/{stateId}")
    public ResponseEntity<?> update(@PathVariable Long stateId,
                                    @RequestBody State state) {
        stateService.updateState(stateId, state);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{stateId}")
    public ResponseEntity<State> delete(@PathVariable Long stateId) {
        stateService.deleteState(stateId);
        return ResponseEntity.noContent().build();
    }

}
