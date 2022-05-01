package entity;

import entity.baseEntity.User;
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
@Table(name = "admins")
public class AdminUser extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Builder
    public AdminUser(String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, byte[] profileImage, Integer id) {
        super(firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.id = id;
    }

    @Override
    public String toString() {
        return "Admin{" + "id=" + id + " ,name=" + getFirstName() + " " + getLastName() + " ,nationalCode=" + getNationalCode() + " ,password=" + getPassword() + '}';
    }
}
