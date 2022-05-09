package com.Maktab.Final.repository;

import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.Offer;
import com.Maktab.Final.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepo extends JpaRepository<Offer, Integer> {
    public Offer findOfferById(Integer id);
    public List<Offer> findOfferByExpert(Expert expert);
    public List<Offer> findOfferByOrder(Order order);
    public void deleteAllByOrder(Order order);
}
