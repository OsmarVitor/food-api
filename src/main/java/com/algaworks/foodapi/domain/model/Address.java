package com.algaworks.foodapi.domain.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Embeddable
public class Address {

    private String zipCode;

    private String publicPlace;

    private String number;

    private String complement;

    private String district;

    @ManyToOne
    @JoinColumn(name = "address_city_id")
    private City city;
}
