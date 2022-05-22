package com.Maktab.Final.model.entity.queryEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ServiceOrder {
    String name;
    Integer id;
    String address;
    String nationalCode;
    String lastName;


    public ServiceOrder(String name, Integer id, String address, String nationalCode, String lastName) {
        this.nationalCode = nationalCode;
        this.name = name;
        this.id = id;
        this.address = address;
        this.lastName = lastName;
    }
}
