package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.Offer;
import com.Maktab.Final.model.entity.Order;

import java.util.List;

public interface OfferServiceInterface {
    public Offer findOfferById(Integer id);
    public List<Offer> findOfferByExpert(Expert expert);
    public List<Offer> findOfferByOrderWithSort(Order order, String sortName);
    public void deleteAllByOrder(Order order);
}
