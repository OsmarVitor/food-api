package com.algaworks.foodapi.domain.model.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {
    CREATED(0),
    CONFIRMED(1),
    DELIVERED(2),
    CANCELED(3);

    private final int status;

    OrderStatus(int status) {
        this.status = status;
    }

}
