package com.Maktab.Final.repository;

import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.CustomerPage;
import com.Maktab.Final.entity.CustomerSearchCriteria;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class CustomerCriteriaRepo {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public CustomerCriteriaRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Customer> findAllWithFilters(CustomerPage customerPage,
                                             CustomerSearchCriteria customerSearchCriteria) {
        CriteriaQuery<Customer> customerCriteriaQuery = criteriaBuilder.createQuery(Customer.class);
        Root<Customer> customerRoot = customerCriteriaQuery.from(Customer.class);
        Predicate predicate = getPredicate(customerSearchCriteria, customerRoot);
        customerCriteriaQuery.where(predicate);
        setOrder(customerPage, customerCriteriaQuery, customerRoot);

        TypedQuery<Customer> typedQuery = entityManager.createQuery(customerCriteriaQuery);
        typedQuery.setFirstResult(customerPage.getPageNumber() * customerPage.getPageSize());
        typedQuery.setMaxResults(customerPage.getPageSize());
        Pageable pageable = getPageable(customerPage);
        long customerCount = getCustomerCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, customerCount);
    }
    private Predicate getPredicate(CustomerSearchCriteria customerSearchCriteria,
                                    Root<Customer> customerRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(customerSearchCriteria.getFirstName())) {
            predicates.add(
                    criteriaBuilder.like(customerRoot.get("firstName"),
                            "%" + customerSearchCriteria.getFirstName() + "%")
            );
        }
        if (Objects.nonNull(customerSearchCriteria.getLastName())) {
            predicates.add(
                    criteriaBuilder.like(customerRoot.get("lastName"),
                            "%" + customerSearchCriteria.getLastName() + "%")
            );
        }
        if (Objects.nonNull(customerSearchCriteria.getNationalCode())) {
            predicates.add(
                    criteriaBuilder.like(customerRoot.get("nationalCode"),
                            "%" + customerSearchCriteria.getNationalCode() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(CustomerPage customerPage, CriteriaQuery<Customer> customerCriteriaQuery, Root<Customer> customerRoot) {
        if (customerPage.getSortDirection().equals(Sort.Direction.ASC)) {
            customerCriteriaQuery.orderBy(criteriaBuilder.asc(customerRoot.get(customerPage.getSortBy())));
        } else {
            customerCriteriaQuery.orderBy(criteriaBuilder.desc(customerRoot.get(customerPage.getSortBy())));
        }
    }

    private Pageable getPageable(CustomerPage customerPage) {
        Sort sort = Sort.by(customerPage.getSortDirection(), customerPage.getSortBy());
        return PageRequest.of(customerPage.getPageNumber(), customerPage.getPageSize() , sort);
    }

    private long getCustomerCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Customer> countRoot = countQuery.from(Customer.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
