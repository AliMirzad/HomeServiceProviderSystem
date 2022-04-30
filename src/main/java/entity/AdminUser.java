package entity;

import entity.baseEntity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "admins")
public class AdminUser extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Builder
    public AdminUser(String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, String profileImage, Integer id) {
        super(firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.id = id;
    }
}
