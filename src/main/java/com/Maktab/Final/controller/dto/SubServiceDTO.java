package com.Maktab.Final.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class SubServiceDTO {
    private Integer id;
    @NotNull
    private String name;
    @NotNull
    private Double basePrice;
    @NotNull
    private String description;
    @NotNull
    private String serviceName;

    @Builder
    public SubServiceDTO(Integer id, String name, Double basePrice, String description, String serviceName) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.description = description;
        this.serviceName = serviceName;
    }
}
