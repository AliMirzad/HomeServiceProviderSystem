package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.Offer;
import com.Maktab.Final.model.entity.Order;

import java.util.List;

public interface OfferServiceInterface {
    Offer findOfferById(Integer id);

    List<Offer> findOfferByExpert(Expert expert);

    List<Offer> findOfferByOrderWithSort(Order order, String sortName);
}
