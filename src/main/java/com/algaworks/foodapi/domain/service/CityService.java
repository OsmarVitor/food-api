package com.algaworks.foodapi.domain.service;

import com.algaworks.foodapi.domain.model.City;
import org.springframework.data.domain.Page;

public interface CityService {

    City create(City city);

    City find(long id);

    City update(long id, City city);

    void delete(Long cityId);

    Page<City> list(int page, int size);

}