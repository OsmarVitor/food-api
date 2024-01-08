package com.algaworks.foodapi.api.controller;

import com.algaworks.foodapi.domain.model.City;
import com.algaworks.foodapi.domain.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    public ResponseEntity<City> createCity(@RequestBody City city) {
        City citySaved = cityService.createCity(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(citySaved);
    }

    @GetMapping
    public ResponseEntity<Page<City>> listCities(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size) {
        return ResponseEntity.status(HttpStatus.OK).body(cityService.listCities(page, size));
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> findCity(@PathVariable Long cityId) {
        City city = cityService.findCity(cityId);
        return ResponseEntity.ok(city);
    }

    @PutMapping("/{cityId}")
    public ResponseEntity<City> updateCity(@PathVariable Long cityId,
                                           @RequestBody City city) {
        cityService.updateCity(cityId, city);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cityId}")
    public ResponseEntity<City> delete(@PathVariable Long cityId) {
        cityService.deleteCity(cityId);
        return ResponseEntity.noContent().build();
    }
}
