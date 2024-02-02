package com.algaworks.foodapi.api.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
public class CityResponse {
    private String cityName;
    @JsonProperty("state")
    private StateResponse stateResponse;
}
