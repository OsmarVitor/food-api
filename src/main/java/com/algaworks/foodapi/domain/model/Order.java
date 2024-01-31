package com.algaworks.foodapi.domain.model;

import com.algaworks.foodapi.domain.model.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "orders")
public class Order {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal netAmount;

    private BigDecimal grossAmount;

    private BigDecimal deliveryFee;

    private Integer quantity;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Address address;

    @Enumerated(EnumType.ORDINAL)
    private OrderStatus orderStatus;

    private LocalDateTime confirmationDate;

    private LocalDateTime cancellationDate;

    private LocalDateTime deliveryDate;

    @ManyToOne
    @JoinColumn(nullable = false)
    private PaymentType paymentType;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Restaurant restaurant;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProduct;

}
