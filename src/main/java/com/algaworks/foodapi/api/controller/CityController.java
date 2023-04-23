package com.algaworks.foodapi.api.controller;

import com.algaworks.foodapi.domain.model.City;
import com.algaworks.foodapi.domain.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public ResponseEntity<Page<City>> list(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "15") int size
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(cityService.list(page, size));
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> find(@PathVariable Long cityId) {
        City city = cityService.find(cityId);
        return ResponseEntity.ok(city);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody City city) {
        City citySaved = cityService.create(city);
        return ResponseEntity.status(HttpStatus.CREATED).body(citySaved);
    }

    @PutMapping("/{cidadeId}")
    public ResponseEntity<?> update(@PathVariable Long cityId,
                                       @RequestBody City city) {
        cityService.update(cityId, city);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{cidadeId}")
    public ResponseEntity<City> delete(@PathVariable Long cityId) {
        cityService.delete(cityId);
        return ResponseEntity.noContent().build();
    }
}
