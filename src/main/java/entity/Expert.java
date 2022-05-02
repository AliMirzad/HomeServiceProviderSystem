package entity;

import entity.baseEntity.User;
import entity.enums.ExpertUserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "experts")
public class Expert extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ExpertUserStatus status;
    private Double point;


    @OneToMany
    private Set<Order> orders;
    @OneToMany
    private Set<SysTransaction> sysTransactions;
    @OneToOne
    private Wallet wallet;

}
