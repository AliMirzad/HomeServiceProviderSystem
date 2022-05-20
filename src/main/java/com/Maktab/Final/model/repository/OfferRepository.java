package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.Offer;
import com.Maktab.Final.model.entity.Order;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OfferRepository extends PagingAndSortingRepository<Offer, Integer> {
    Offer findOfferById(Integer id);
    List<Offer> findOfferByExpert(Expert expert);
    List<Offer> findOfferByOrder(Order order, Sort sort);
}
