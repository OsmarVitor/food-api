package com.algaworks.foodapi.domain.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Builder
@Data
public class Address {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String number;

    private String neighborhood;

    private String zipCode;

    private String complement;

    @ManyToOne
    @JoinColumn(name = "address_city_id")
    private City city;
}
