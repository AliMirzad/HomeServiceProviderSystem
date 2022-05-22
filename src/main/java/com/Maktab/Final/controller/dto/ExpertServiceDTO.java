package com.Maktab.Final.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExpertServiceDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String nationalCode;

    public ExpertServiceDTO(Integer id, String firstName, String lastName, String email, String nationalCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nationalCode = nationalCode;
    }
}
