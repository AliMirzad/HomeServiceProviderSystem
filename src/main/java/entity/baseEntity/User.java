package entity.baseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@MappedSuperclass
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String nationalCode;
    private String password;
    private LocalDateTime registerTime;
    private String profileImage;
}
