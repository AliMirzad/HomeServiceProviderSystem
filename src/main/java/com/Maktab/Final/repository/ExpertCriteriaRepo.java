package com.Maktab.Final.repository;

import com.Maktab.Final.entity.*;
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
public class ExpertCriteriaRepo {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ExpertCriteriaRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Expert> findAllWithFilters(ExpertPage expertPage,
                                           ExpertSearchCriteria expertSearchCriteria) {
        CriteriaQuery<Expert> expertCriteriaQuery = criteriaBuilder.createQuery(Expert.class);
        Root<Expert> expertRoot = expertCriteriaQuery.from(Expert.class);
        Predicate predicate = getPredicate(expertSearchCriteria, expertRoot);
        expertCriteriaQuery.where(predicate);
        setOrder(expertPage, expertCriteriaQuery, expertRoot);

        TypedQuery<Expert> typedQuery = entityManager.createQuery(expertCriteriaQuery);
        typedQuery.setFirstResult(expertPage.getPageNumber() * expertPage.getPageSize());
        typedQuery.setMaxResults(expertPage.getPageSize());
        Pageable pageable = getPageable(expertPage);
        long expertCount = getExpertCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, expertCount);
    }
    private Predicate getPredicate(ExpertSearchCriteria expertSearchCriteria,
                                   Root<Expert> expertRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(expertSearchCriteria.getFirstName())) {
            predicates.add(
                    criteriaBuilder.like(expertRoot.get("firstName"),
                            "%" + expertSearchCriteria.getFirstName() + "%")
            );
        }
        if (Objects.nonNull(expertSearchCriteria.getLastName())) {
            predicates.add(
                    criteriaBuilder.like(expertRoot.get("lastName"),
                            "%" + expertSearchCriteria.getLastName() + "%")
            );
        }
        if (Objects.nonNull(expertSearchCriteria.getNationalCode())) {
            predicates.add(
                    criteriaBuilder.like(expertRoot.get("nationalCode"),
                            "%" + expertSearchCriteria.getNationalCode() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(ExpertPage expertPage, CriteriaQuery<Expert> expertCriteriaQuery, Root<Expert> expertRoot) {
        if (expertPage.getSortDirection().equals(Sort.Direction.ASC)) {
            expertCriteriaQuery.orderBy(criteriaBuilder.asc(expertRoot.get(expertPage.getSortBy())));
        } else {
            expertCriteriaQuery.orderBy(criteriaBuilder.desc(expertRoot.get(expertPage.getSortBy())));
        }
    }

    private Pageable getPageable(ExpertPage expertPage) {
        Sort sort = Sort.by(expertPage.getSortDirection(), expertPage.getSortBy());
        return PageRequest.of(expertPage.getPageNumber(), expertPage.getPageSize() , sort);
    }

    private long getExpertCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Expert> countRoot = countQuery.from(Expert.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
