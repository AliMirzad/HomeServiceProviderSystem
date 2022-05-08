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
@DiscriminatorValue("customer")
@Table(name = "customers")
public class Customer extends User {
    //-----------------------------------------------fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private CustomerUserStatus status;
    //-----------------------------------------------relation
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
    @OneToMany(mappedBy = "customer")
    private Set<Comment> comments;
    @OneToOne(mappedBy = "customer")
    private Wallet wallet;
    @OneToMany(mappedBy = "customer")
    private Set<Transactions> transactions;
    //----------------------------------------------toString, Cons
    @Builder
    public Customer(Integer id, String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage, Integer id1, CustomerUserStatus status, Set<Order> orders, Set<Comment> comments, Wallet wallet, Set<Transactions> transactions) {
        super(id, firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.id = id1;
        this.status = status;
        this.orders = orders;
        this.comments = comments;
        this.wallet = wallet;
        this.transactions = transactions;
    }
}
