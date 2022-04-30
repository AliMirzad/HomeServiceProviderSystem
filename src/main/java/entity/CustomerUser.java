package entity;

import entity.baseEntity.User;
import entity.enums.CustomerUserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "customers")
public class CustomerUser extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private CustomerUserStatus status;
    @OneToOne
    private Wallet wallet;

    @Builder
    public CustomerUser(String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, String profileImage, Integer id, CustomerUserStatus status) {
        super(firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.id = id;
        this.status = status;
    }
}
