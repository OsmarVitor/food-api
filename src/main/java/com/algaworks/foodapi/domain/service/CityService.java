package com.algaworks.foodapi.domain.service;

import com.algaworks.foodapi.domain.model.City;
import org.springframework.data.domain.Page;

public interface CityService {

    City createCity(City city);

    City findCity(long id);

    City updateCity(long id, City city);

    void deleteCity(Long cityId);

    Page<City> listCities(int page, int size);

}