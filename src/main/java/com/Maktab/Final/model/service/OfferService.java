package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.Offer;
import com.Maktab.Final.model.entity.Order;
import com.Maktab.Final.model.repository.OfferRepository;
import com.Maktab.Final.model.service.serviceInterface.OfferServiceInterface;
import com.Maktab.Final.model.exception.LogicErrorException;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OfferService implements OfferServiceInterface {
    //---------------------------------------------------------needed
    private final OfferRepository offerRepository;
    private final OrderService orderService;
    private final ExpertService expertService;

    public OfferService(OfferRepository offerRepository, @Lazy OrderService orderService, ExpertService expertService) {
        this.offerRepository = offerRepository;
        this.orderService = orderService;
        this.expertService = expertService;
    }

    //---------------------------------------------------------methods
    @Override
    public Offer findOfferById(Integer id) {
        Offer offer = offerRepository.findOfferById(id);
        if (offer == null) throw new LogicErrorException("offer not found");
        return offer;
    }

    @Override
    public List<Offer> findOfferByExpert(Expert expert) {
        if (expertService.findExpertById(expert.getId()) == null)
            throw new LogicErrorException("expert offer not found");
        List<Offer> offers = offerRepository.findOfferByExpert(expert);
        if (offers == null) throw new LogicErrorException("offer list is empty");
        return offers;
    }

    @Override
    public List<Offer> findOfferByOrderWithSort(Order order, String sortName) {
        return offerRepository.findOfferByOrder(order, Sort.by(Sort.Direction.DESC, sortName));
    }

    @Override
    public void deleteAllByOrder(Order order) {
        if (orderService.findOrderById(order.getId()) == null) throw new LogicErrorException("order offer not found");
        offerRepository.deleteAllByOrder(order);
    }


    public void create(Offer offer) {
        if (offer.getId() != null) throw new LogicErrorException("offer id must be null/empty");
        if (orderService.findOrderById(offer.getOrder().getId()) == null) throw new LogicErrorException("order offer not found");
        if (expertService.findExpertById(offer.getExpert().getId()) == null) throw new LogicErrorException("expert offer not found");
        if (offer.getOfferPrice() < offer.getOrder().getSuggestPrice() || offer.getOfferPrice() == null)
            throw new LogicErrorException("offer price must be more then suggested price");
        if (offer.getEndTime().isBefore(offer.getStartTime()))
            throw new LogicErrorException("offer end time must be after start time");
        if (offer.getStartTime().isBefore(offer.getOrder().getOrderRegisterTime()))
            throw new LogicErrorException("offer start time must be after order register time");
        offerRepository.save(offer);
    }
}
