package entity;

import entity.baseEntity.User;
import entity.enums.ExpertUserStatus;
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
@DiscriminatorValue("expert")
@Table(name = "experts")
public class Expert extends User {
    //---------------------------------------------------fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private ExpertUserStatus status;
    private Integer point;
    //---------------------------------------------------relations
    @OneToMany(mappedBy = "expert")
    private Set<Services> services;
    @OneToMany(mappedBy = "expert")
    private Set<Offer> offers;
    @OneToMany(mappedBy = "expert")
    private Set<Comment> comments;
    @OneToOne(mappedBy = "expert")
    private Wallet wallet;
    @OneToMany(mappedBy = "expert")
    private Set<Transactions> transactions;

    //----------------------------------------------toString, cons
    @Builder
    public Expert(Integer id, String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage, Integer id1, ExpertUserStatus status, Integer point, Set<Services> services, Set<Offer> offers, Set<Comment> comments, Wallet wallet, Set<Transactions> transactions) {
        super(id, firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.id = id1;
        this.status = status;
        this.point = point;
        this.services = services;
        this.offers = offers;
        this.comments = comments;
        this.wallet = wallet;
        this.transactions = transactions;
    }
}
