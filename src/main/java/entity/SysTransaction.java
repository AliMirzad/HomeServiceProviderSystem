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
@Table(name = "sys_transactions")
public class SysTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private TransactionModel model;
    private Double amount;
    private Double remain;
    private Integer walletId;

    @Builder
    public SysTransaction(Integer id, TransactionModel model, Double amount, Double remain, Integer walletId) {
        this.id = id;
        this.model = model;
        this.amount = amount;
        this.remain = remain;
        this.walletId = walletId;
    }
}
