package com.Maktab.Final.model.util;


import com.Maktab.Final.model.entity.baseEntity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserGridSearch {

	public Specification<User> gridSearch(Integer userId, String email, String firstName, String lastName/*, LocalDateTime registerDate, Integer orderCount, Integer offerCount*/) {
		return (root, query, criteriaBuilder) -> {

			List<Predicate> predicates = new ArrayList<>();

			if (userId != null) {
				predicates.add(criteriaBuilder.equal(root.get("id"), userId));
			}

			if (email != null && !email.isEmpty()) {
				predicates.add(criteriaBuilder.equal(root.get("email"), email));
			}

			if (firstName != null && !firstName.isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
			}

			if (lastName != null && !lastName.isEmpty()) {
				predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"));
			}

//			if (registerDate != null) {
//				predicates.add(criteriaBuilder.like(root.get("registerDate"), "%" + registerDate + "%"));
//			}
//
//			if (orderCount != null) {
//				predicates.add(criteriaBuilder.like(root.get("orderCount"), "%" + orderCount + "%"));
//			}
//
//			if (offerCount != null) {
//				predicates.add(criteriaBuilder.like(root.get("offerCount"), "%" + offerCount + "%"));
//			}

			query.orderBy(criteriaBuilder.asc(root.get("lastName")));
			return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
		};
	}
}

