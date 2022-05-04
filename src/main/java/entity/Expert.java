package entity;

import entity.baseEntity.User;
import entity.enums.ExpertUserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.security.Provider;
import java.time.LocalDateTime;
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
    private Integer point;


    @OneToMany(fetch = FetchType.EAGER)
    private Set<Offer> offers;
    @OneToMany
    private Set<SysTransaction> sysTransactions;
    @OneToOne
    private Wallet wallet;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<SysService> services;
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Comment> comments;

    @Builder
    public Expert(String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage, Integer id, ExpertUserStatus status, Integer point, Set<Offer> offers, Set<SysTransaction> sysTransactions, Wallet wallet, Set<SysService> services, Set<Comment> comments) {
        super(firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.id = id;
        this.status = status;
        this.point = point;
        this.offers = offers;
        this.sysTransactions = sysTransactions;
        this.wallet = wallet;
        this.services = services;
        this.comments = comments;
    }
}
