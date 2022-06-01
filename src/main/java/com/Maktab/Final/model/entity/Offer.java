package com.Maktab.Final.model.entity;

import com.Maktab.Final.model.entity.enums.OfferStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Enumerated(EnumType.STRING)
    private OfferStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Expert expert;
    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    @Builder
    public Offer(Integer id, LocalDateTime registrationDateTime, Double offerPrice, LocalDateTime startTime, LocalDateTime endTime, OfferStatus status, Expert expert, Order order) {
        this.id = id;
        this.registrationDateTime = registrationDateTime;
        this.offerPrice = offerPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.expert = expert;
        this.order = order;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", registrationDateTime=" + registrationDateTime +
                ", offerPrice=" + offerPrice +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", expert=" + expert +
                ", order=" + order +
                '}';
    }
}
