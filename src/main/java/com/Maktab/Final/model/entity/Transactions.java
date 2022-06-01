package com.Maktab.Final.model.entity;

import com.Maktab.Final.model.entity.enums.TransactionModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "transactions")
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TransactionModel model;
    private Double remain;
    private Double amount;
    private Integer walletId;
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Expert expert;

    @Builder
    public Transactions(Integer id, TransactionModel model, Double remain, Double amount, Integer walletId, Customer customer, Expert expert) {
        this.id = id;
        this.model = model;
        this.remain = remain;
        this.amount = amount;
        this.walletId = walletId;
        this.customer = customer;
        this.expert = expert;
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "id=" + id +
                ", model=" + model +
                ", remain=" + remain +
                ", amount=" + amount +
                ", walletId=" + walletId +
                ", customer=" + customer +
                ", expert=" + expert +
                '}';
    }
}
