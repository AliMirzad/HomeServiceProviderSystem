package com.Maktab.Final.controller.dto;

import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Expert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.OneToOne;

@Getter
@Setter
@NoArgsConstructor
public class WalletDTO {
    private Integer id;
    private Double balance;
    private String nationalCode;
}
