package com.algaworks.foodapi.domain.service.impl;

import com.algaworks.foodapi.api.controller.request.CityRequest;
import com.algaworks.foodapi.api.controller.response.CityResponse;
import com.algaworks.foodapi.domain.exception.EntityInUseException;
import com.algaworks.foodapi.domain.exception.EntityNotFoundException;
import com.algaworks.foodapi.domain.model.City;
import com.algaworks.foodapi.domain.model.State;
import com.algaworks.foodapi.domain.repository.CityRepository;
import com.algaworks.foodapi.domain.service.CityService;
import com.algaworks.foodapi.domain.service.StateService;
import com.algaworks.foodapi.domain.service.converter.CityConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private final StateService stateService;

    @Override
    public Page<CityResponse> listCities(int page, int size) {
        return cityRepository.findAll(PageRequest.of(page, size)).map(city -> CityConverter.cityToResponse(city, city.getState()));
    }

    @Override
    public CityResponse findCity(long id) {
        City city = findCityById(id);
        return CityConverter.cityToResponse(city, city.getState());
    }

    @Override
    public CityResponse createCity(CityRequest cityRequest) {
        State state = stateService.findState(cityRequest.getStateId());
        if (Objects.isNull(state)) {
            throw new EntityNotFoundException(cityRequest.getStateId());
        }
        City city = City.builder().name(cityRequest.getName()).build();
        city.setState(state);
        return CityConverter.cityToResponse(cityRepository.save(city), state);
    }

    @Override
    public CityResponse updateCity(long id, City cityUpdated) {
        City cityToUpdate = findCityById(id);
        BeanUtils.copyProperties(cityUpdated, cityToUpdate, "id");
        return CityConverter.cityToResponse(cityRepository.save(cityToUpdate), cityToUpdate.getState());
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
