package com.Maktab.Final.model.entity;

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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double balance;
    @OneToOne
    private Customer customer;
    @OneToOne
    private Expert expert;

    @Builder
    public Wallet(Integer id, Double balance, Customer customer, Expert expert) {
        this.id = id;
        this.balance = balance;
        this.customer = customer;
        this.expert = expert;
    }

    @Override
    public String toString() {
        return "Wallet{" +
                "id=" + id +
                ", balance=" + balance +
                ", customer=" + customer +
                ", expert=" + expert +
                '}';
    }
}
