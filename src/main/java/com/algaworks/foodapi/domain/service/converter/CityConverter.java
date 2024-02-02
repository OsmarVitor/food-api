package com.algaworks.foodapi.domain.service.converter;

import com.algaworks.foodapi.api.controller.response.CityResponse;
import com.algaworks.foodapi.api.controller.response.StateResponse;
import com.algaworks.foodapi.domain.model.City;
import com.algaworks.foodapi.domain.model.State;

public class CityConverter {

    public static CityResponse cityToResponse(City city, State state){
        return CityResponse.builder()
                .cityName(city.getName())
                .stateResponse(StateResponse.builder()
                        .name(state.getName())
                        .build())
                .build();
    }
}
