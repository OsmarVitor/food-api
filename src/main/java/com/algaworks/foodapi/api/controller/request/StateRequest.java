package com.algaworks.foodapi.api.controller.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StateRequest {
    private String name;
}
