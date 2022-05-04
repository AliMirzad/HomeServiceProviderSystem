package entity;

import entity.enums.OrderStatus;
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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private Double offerPrice;
    @Column(unique = true)
    private String description;
    private LocalDateTime orderRegisterTime;
    @Column(unique = true)
    private String address;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    @OneToMany(fetch = FetchType.EAGER)
    private Set<Offer> offers;


    @Builder
    public Order(Integer id, Double offerPrice, String description, LocalDateTime orderRegisterTime, String address, OrderStatus status, Set<Offer> offers) {
        this.id = id;
        this.offerPrice = offerPrice;
        this.description = description;
        this.orderRegisterTime = orderRegisterTime;
        this.address = address;
        this.status = status;
        this.offers = offers;
    }
}
