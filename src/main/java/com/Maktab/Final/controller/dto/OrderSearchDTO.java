package com.Maktab.Final.controller.dto;

import com.Maktab.Final.model.entity.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class OrderSearchDTO {
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	private OrderStatus status;
	private String subService;
	private String serviceName;
}
