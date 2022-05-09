package com.Maktab.Final.entity;

import com.Maktab.Final.entity.enums.OfferStatus;
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
    //-----------------------------------------------------------fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime registrationDateTime;
    private Double offerPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private OfferStatus status;
    //--------------------------------------------------------------relations
    @ManyToOne
    private Expert expert;
    @ManyToOne
    private Order order;

    //--------------------------------------------------------------toString, cons
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
}
