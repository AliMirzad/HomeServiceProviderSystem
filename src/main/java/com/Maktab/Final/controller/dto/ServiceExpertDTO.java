package com.Maktab.Final.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServiceExpertDTO {
    private String expertNationalCode;
    private String subServiceName;

    @Builder
    public ServiceExpertDTO(String expertNationalCode, String subServiceName) {
        this.expertNationalCode = expertNationalCode;
        this.subServiceName = subServiceName;
    }
}
