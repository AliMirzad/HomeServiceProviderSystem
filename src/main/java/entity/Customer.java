package entity;

import entity.baseEntity.User;
import entity.enums.CustomerUserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
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


    @OneToMany(fetch = FetchType.EAGER)
    private Set<Order> orders;
    @OneToOne
    private Wallet wallet;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<SysTransaction> sysTransactions;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Comment> comments;

    @Builder
    public Customer(String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage, Integer id, CustomerUserStatus status, Set<Order> orders, Wallet wallet, Set<SysTransaction> sysTransactions, Set<Comment> comments) {
        super(firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.id = id;
        this.status = status;
        this.orders = orders;
        this.wallet = wallet;
        this.sysTransactions = sysTransactions;
        this.comments = comments;
    }
}
