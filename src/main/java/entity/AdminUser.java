package entity;

import entity.baseEntity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
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
