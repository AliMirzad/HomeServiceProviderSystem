package com.Maktab.Final.repository;

import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.Offer;
import com.Maktab.Final.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OfferRepo extends PagingAndSortingRepository<Offer, Integer> {
    public Offer findOfferById(Integer id);
    public List<Offer> findOfferByExpert(Expert expert);
    public List<Offer> findOfferByOrder(Order order, Sort sort);
    public void deleteAllByOrder(Order order);

}
