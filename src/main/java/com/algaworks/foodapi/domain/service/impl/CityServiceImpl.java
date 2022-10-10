package com.algaworks.foodapi.domain.service.impl;

import com.algaworks.foodapi.domain.exception.EntityInUseException;
import com.algaworks.foodapi.domain.exception.EntityNotFoundException;
import com.algaworks.foodapi.domain.model.City;
import com.algaworks.foodapi.domain.model.State;
import com.algaworks.foodapi.domain.repository.CityRepository;
import com.algaworks.foodapi.domain.service.CityService;
import com.algaworks.foodapi.domain.service.StateService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateService stateService;

    @Override
    public Page<City> list(int page, int size) {
        return cityRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public City find(long id) {
        return findCity(id);
    }

    @Override
    public City create(City city) {

        Long stateId = city.getState().getId();
        State state = stateService.find(stateId);
        if (Objects.isNull(state)) {
            throw new EntityNotFoundException(stateId);
        }
        city.setState(state);
        return cityRepository.save(city);
    }

    @Override
    public City update(long id, City cityUpdated) {
        City cityToUpdate = findCity(id);
        BeanUtils.copyProperties(cityUpdated, cityToUpdate, "id");
        return cityRepository.save(cityUpdated);
    }

    @Override
    public void delete(Long cityId) {
        City city = findCity(cityId);
        try {
            cityRepository.delete(city);
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(
                    String.format("City with id %d cannot be deleted because it is in use", cityId));
        }
    }

    private City findCity(long cityId) {
        return cityRepository.findById(cityId).orElseThrow(() -> new EntityNotFoundException(cityId));
    }

}
