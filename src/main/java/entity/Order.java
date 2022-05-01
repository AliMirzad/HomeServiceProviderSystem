package entity;

import entity.enums.OrderStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Getter
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
    private LocalDateTime registrationTime;
    private LocalDateTime serviceTime;
    @Column(unique = true)
    private String address;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @ManyToOne
    private Service service;
    @OneToMany
    private Set<Offer> offers;

    @Builder
    public Order(Integer id, Double offerPrice, String description, LocalDateTime registrationTime, LocalDateTime serviceTime, String address, OrderStatus status, Service service) {
        this.id = id;
        this.offerPrice = offerPrice;
        this.description = description;
        this.registrationTime = registrationTime;
        this.serviceTime = serviceTime;
        this.address = address;
        this.status = status;
        this.service = service;
    }
}
