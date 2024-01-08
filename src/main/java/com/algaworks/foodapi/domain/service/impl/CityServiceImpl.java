package com.algaworks.foodapi.domain.service.impl;

import com.algaworks.foodapi.domain.exception.EntityInUseException;
import com.algaworks.foodapi.domain.exception.EntityNotFoundException;
import com.algaworks.foodapi.domain.model.City;
import com.algaworks.foodapi.domain.model.State;
import com.algaworks.foodapi.domain.repository.CityRepository;
import com.algaworks.foodapi.domain.service.CityService;
import com.algaworks.foodapi.domain.service.StateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final StateService stateService;

    @Override
    public Page<City> listCities(int page, int size) {
        return cityRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public City findCity(long id) {
        return findCityById(id);
    }

    @Override
    public City createCity(City city) {

        Long stateId = city.getState().getId();
        State state = stateService.findState(stateId);
        if (Objects.isNull(state)) {
            throw new EntityNotFoundException(stateId);
        }
        city.setState(state);
        return cityRepository.save(city);
    }

    @Override
    public City updateCity(long id, City cityUpdated) {
        City cityToUpdate = findCityById(id);
        BeanUtils.copyProperties(cityUpdated, cityToUpdate, "id");
        return cityRepository.save(cityToUpdate);
    }

    @Override
    public void deleteCity(Long cityId) {
        City city = findCityById(cityId);
        try {
            cityRepository.delete(city);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("City with id %d cannot be deleted because it is in use", cityId));
        }
    }

    private City findCityById(long cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException(cityId));
    }

}
