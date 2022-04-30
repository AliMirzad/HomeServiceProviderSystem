package entity;

import entity.baseEntity.User;
import entity.enums.ExpertUserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Getter
@Entity
public class ExpertUser extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private ExpertUserStatus status;
    @OneToMany
    private Set<Offer> offers;

    @Builder
    public ExpertUser(String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, String profileImage, Integer id, ExpertUserStatus status, Set<Offer> offers) {
        super(firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.id = id;
        this.status = status;
        this.offers = offers;
    }
}
