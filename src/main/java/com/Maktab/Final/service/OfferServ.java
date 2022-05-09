package com.Maktab.Final.service;

import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.Offer;
import com.Maktab.Final.entity.Order;
import com.Maktab.Final.repository.OfferRepo;
import com.Maktab.Final.service.serviceInterface.OfferServInt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OfferServ implements OfferServInt {
    //---------------------------------------------------------needed
    private final OfferRepo offerRepo;

    public OfferServ(OfferRepo offerRepo) {
        this.offerRepo = offerRepo;
    }

    //---------------------------------------------------------methods
    @Override
    public Offer findOfferById(Integer id) {
        return offerRepo.findOfferById(id);
    }

    @Override
    public List<Offer> findOfferByExpert(Expert expert) {
        return offerRepo.findOfferByExpert(expert);
    }

    @Override
    public List<Offer> findOfferByOrder(Order order) {
        return offerRepo.findOfferByOrder(order);
    }

    @Override
    public void deleteAllByOrder(Order order) {
        offerRepo.deleteAllByOrder(order);
    }

    @Transactional
    public void save(Offer offer) {
        offerRepo.save(offer);
    }
}
