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
@Table(name = "experts")
public class ExpertUser extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private ExpertUserStatus status;
    private Double point;
    @OneToMany
    private Set<Offer> offers;
    @OneToOne
    private Wallet wallet;

    @Builder
    public ExpertUser(String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage, Integer id, ExpertUserStatus status, Double point, Set<Offer> offers) {
        super(firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.id = id;
        this.status = status;
        this.point = point;
        this.offers = offers;
    }
}
