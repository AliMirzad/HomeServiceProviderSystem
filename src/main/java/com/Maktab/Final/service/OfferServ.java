package com.Maktab.Final.service;

import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.Offer;
import com.Maktab.Final.entity.Order;
import com.Maktab.Final.repository.OfferRepo;
import com.Maktab.Final.service.serviceInterface.OfferServInt;
import exception.LogicException;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OfferServ implements OfferServInt {
    //---------------------------------------------------------needed
    private final OfferRepo offerRepo;
    private final OrderServ orderServ;

    public OfferServ(OfferRepo offerRepo,@Lazy OrderServ orderServ) {
        this.offerRepo = offerRepo;
        this.orderServ = orderServ;
    }

    //---------------------------------------------------------methods
    @Override
    public Offer findOfferById(Integer id) {
        try {
            Offer offer = offerRepo.findOfferById(id);
            if (offer == null) throw new LogicException("we dont have this offer");
            return offer;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public List<Offer> findOfferByExpert(Expert expert) {
        try {
            List<Offer> offers = offerRepo.findOfferByExpert(expert);
            if (offers == null) throw new LogicException("offers is null here");
            return offers;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public void deleteAllByOrder(Order order) {
        try {
            Order order1 = orderServ.findOrderById(order.getId());
            if (order1 == null) throw new LogicException("we dont have this order");
            offerRepo.deleteAllByOrder(order);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Transactional
    public void save(Offer offer) {
        try {
            if (offer.getOfferPrice() < offer.getOrder().getSuggestPrice() || offer.getOfferPrice() == null) throw new LogicException("offer price must be more then suggest price");
            if (offer.getEndTime().isBefore(offer.getStartTime())) throw new LogicException("offer end time must be after start time");
            if (offer.getStartTime().isBefore(offer.getOrder().getOrderRegisterTime())) throw new LogicException("offer must be after order time");
            offerRepo.save(offer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Override
    public List<Offer> findOfferByOrderWithSort(Order order, String sortName) {
        return offerRepo.findOfferByOrder(order, Sort.by(Sort.Direction.DESC, sortName));
    }
}
