package com.Maktab.Final.model.entity;

import com.Maktab.Final.model.entity.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    //--------------------------------------------------------fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime orderRegisterTime;
    private Double suggestPrice;
    private String address;
    private String description;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    //----------------------------------------------------relations
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private SubService subService;

    //------------------------------------------------------toString, cons
    @Builder
    public Order(Integer id, LocalDateTime orderRegisterTime, Double suggestPrice, String address, String description, OrderStatus status, Customer customer, SubService subService) {
        this.id = id;
        this.orderRegisterTime = orderRegisterTime;
        this.suggestPrice = suggestPrice;
        this.address = address;
        this.description = description;
        this.status = status;
        this.customer = customer;
        this.subService = subService;
    }
}
