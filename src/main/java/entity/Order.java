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
    @ManyToOne
    private CustomerUser customerUser;
    @ManyToOne
    private Service service;

    @Builder
    public Order(Integer id, Double offerPrice, String description, LocalDateTime orderRegisterTime, String address, OrderStatus status, CustomerUser customerUser, Service service) {
        this.id = id;
        this.offerPrice = offerPrice;
        this.description = description;
        this.orderRegisterTime = orderRegisterTime;
        this.address = address;
        this.status = status;
        this.customerUser = customerUser;
        this.service = service;
    }
}
