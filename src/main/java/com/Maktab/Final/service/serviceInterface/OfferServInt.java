package com.Maktab.Final.service.serviceInterface;

import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.Offer;
import com.Maktab.Final.entity.Order;

import java.util.List;

public interface OfferServInt {
    public Offer findOfferById(Integer id);
    public List<Offer> findOfferByExpert(Expert expert);
    public List<Offer> findOfferByOrderWithSort(Order order, String sortName);
    public void deleteAllByOrder(Order order);
}
