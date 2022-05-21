package com.Maktab.Final.controller.dto;

import com.Maktab.Final.model.entity.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO {
    private Integer id;
    private Double suggestPrice;
    private String address;
    private String description;
    private String nationalCode;
    private String subServiceName;

    @Builder
    public OrderDTO(Integer id, Double suggestPrice, String address, String description, String nationalCode, String subServiceName) {
        this.id = id;
        this.suggestPrice = suggestPrice;
        this.address = address;
        this.description = description;
        this.nationalCode = nationalCode;
        this.subServiceName = subServiceName;
    }
}
