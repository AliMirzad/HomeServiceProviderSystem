package com.Maktab.Final.model.entity.queryEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserGridSearchQ {
	private Integer userId;
	private String email;
	private String firstName;
	private String lastName;
	private LocalDateTime registerDate;
	private Integer orderCount;
	private Integer offerCount;

	public UserGridSearchQ(Integer userId, String email, String firstName, String lastName, LocalDateTime registerDate, Integer orderCount, Integer offerCount) {
		this.userId = userId;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registerDate = registerDate;
		this.orderCount = orderCount;
		this.offerCount = offerCount;
	}
}
