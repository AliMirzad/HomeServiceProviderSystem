package entity;

import entity.baseEntity.User;
import entity.enums.CustomerUserStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customers")
public class CustomerUser extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    private CustomerUserStatus status;
    @OneToOne
    private Wallet wallet;

    @Builder
    public CustomerUser(String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage, Integer id, CustomerUserStatus status) {
        super(firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + " ,name=" + getFirstName() + " " + getLastName() + " ,nationalCode=" + getNationalCode() + " ,password=" + getPassword() + '}';
    }
}
