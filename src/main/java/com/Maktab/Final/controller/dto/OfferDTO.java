package com.Maktab.Final.controller.dto;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.Order;
import com.Maktab.Final.model.entity.enums.OfferStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OfferDTO {
    private Integer id;
    private Double offerPrice;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String nationalCode;
    private Integer orderId;

    @Builder
    public OfferDTO(Integer id, Double offerPrice, LocalDateTime startTime, LocalDateTime endTime, String nationalCode, Integer orderId) {
        this.id = id;
        this.offerPrice = offerPrice;
        this.startTime = startTime;
        this.endTime = endTime;
        this.nationalCode = nationalCode;
        this.orderId = orderId;
    }
}
