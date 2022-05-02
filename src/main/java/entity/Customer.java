package entity;

import entity.baseEntity.User;
import entity.enums.CustomerUserStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private CustomerUserStatus status;


    @OneToMany
    private Set<Offer> offers;
    @OneToOne
    private Wallet wallet;
    @OneToMany
    private Set<SysTransaction> sysTransactions;
    @OneToMany
    private Set<Comment> comments;
}
