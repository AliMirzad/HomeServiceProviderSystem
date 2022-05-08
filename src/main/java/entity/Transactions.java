package entity;

import entity.enums.TransactionModel;
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
    //---------------------------------------relations
    @ManyToOne
    private Customer customer;
    @ManyToOne
    private Expert expert;

    //---------------------------------------toString, cons
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
}