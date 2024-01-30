package com.algaworks.foodapi.domain.model;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@Embeddable
public class Address {
    private String street;

    private String number;

    private String neighborhood;

    private String zipCode;

    private String complement;

    @ManyToOne
    @JoinColumn(name = "address_city_id")
    private City city;
}
