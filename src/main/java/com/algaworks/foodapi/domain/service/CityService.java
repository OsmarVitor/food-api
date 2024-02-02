package com.algaworks.foodapi.domain.service;

import com.algaworks.foodapi.api.controller.request.CityRequest;
import com.algaworks.foodapi.api.controller.response.CityResponse;
import com.algaworks.foodapi.domain.model.City;
import org.springframework.data.domain.Page;

public interface CityService {

    CityResponse createCity(CityRequest cityRequest);

    CityResponse findCity(long id);

    CityResponse updateCity(long id, City city);

    void deleteCity(Long cityId);

    Page<CityResponse> listCities(int page, int size);

}