package entity;

import entity.baseEntity.User;
import entity.enums.ExpertUserStatus;
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
public class ExpertUser extends User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private ExpertUserStatus status;

    @Builder
    public ExpertUser(String firstName, String lastName, String email, String nationalCode, String password, LocalDateTime registerTime, String profileImage, Integer id, ExpertUserStatus status) {
        super(firstName, lastName, email, nationalCode, password, registerTime, profileImage);
        this.id = id;
        this.status = status;
    }
}
