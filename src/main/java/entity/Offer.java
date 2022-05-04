package entity;

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
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime registrationDateTime;
    private Double offerPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @Builder
    public Offer(Integer id, LocalDateTime registrationDateTime, Double offerPrice, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.registrationDateTime = registrationDateTime;
        this.offerPrice = offerPrice;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
