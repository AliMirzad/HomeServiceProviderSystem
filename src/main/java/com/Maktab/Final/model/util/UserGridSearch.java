package com.Maktab.Final.model.util;


import com.Maktab.Final.model.entity.baseEntity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserGridSearch {

    public Specification<User> gridSearch(Integer userId, String email, String firstName, String lastName, String type) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (userId != null) {
                predicates.add(criteriaBuilder.equal(root.get("userId"), userId));
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

            if (type != null && !type.isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("type"), "%" + type + "%"));
            }

            query.orderBy(criteriaBuilder.asc(root.get("lastName")));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

