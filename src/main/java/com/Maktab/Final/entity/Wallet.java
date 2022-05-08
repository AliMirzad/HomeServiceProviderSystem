package com.Maktab.Final.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "wallets")
public class Wallet {
    //------------------------------------------------------fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double balance;
    //-------------------------------------------------relations
    @OneToOne
    private Customer customer;
    @OneToOne
    private Expert expert;

    //-------------------------------------------------toString, cons
    @Builder
    public Wallet(Integer id, Double balance, Customer customer, Expert expert) {
        this.id = id;
        this.balance = balance;
        this.customer = customer;
        this.expert = expert;
    }
}
