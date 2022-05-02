package entity.baseEntity;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String nationalCode;
    private String password;
    private LocalDateTime registerTime;
    private byte[] profileImage;
}
