package com.Maktab.Final.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ServiceDTO {
    private Integer id;
    @NotNull
    private String name;

    @Builder
    public ServiceDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
