package entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "offers")
public class Offer {
    //-----------------------------------------------------------fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime registrationDateTime;
    private Double offerPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
//--------------------------------------------------------------relations
    @ManyToOne
    private Expert expert;
    @ManyToOne
    private Order order;
//--------------------------------------------------------------toString, cons
    @Builder
    public Offer(Integer id, LocalDateTime registrationDateTime, Double offerPrice, LocalDateTime startTime, LocalDateTime endTime, Expert expert, Order order) {
        this.id = id;
        this.registrationDateTime = registrationDateTime;
        this.offerPrice = offerPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.expert = expert;
        this.order = order;
    }
}
