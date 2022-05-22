package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.Offer;
import com.Maktab.Final.model.entity.Order;

import java.util.List;

public interface OfferServiceInterface {
    Offer findOfferById(Integer id);

    List<Offer> findOffersByExpert(String expertNationalCode);

    List<Offer> findOfferByOrderWithSort(Integer orderId, String sortName, String nationalCode);
}
