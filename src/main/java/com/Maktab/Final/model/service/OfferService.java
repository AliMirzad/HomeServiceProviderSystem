package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.Offer;
import com.Maktab.Final.model.entity.Order;
import com.Maktab.Final.model.entity.enums.OfferStatus;
import com.Maktab.Final.model.entity.enums.OrderStatus;
import com.Maktab.Final.model.repository.OfferRepository;
import com.Maktab.Final.model.service.serviceInterface.OfferServiceInterface;
import com.Maktab.Final.model.exception.LogicErrorException;
import org.aspectj.weaver.ast.Or;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Status;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
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
    public List<Offer> findOfferByOrderWithSort(Integer orderId, String sortName, String nationalCode) {
        Order order = orderService.findOrderById(orderId);
        if (order == null) throw new LogicErrorException("order offer sort not found");
        if (!order.getCustomer().getNationalCode().equals(nationalCode)) throw new LogicErrorException("customer and order not match");
        return offerRepository.findOfferByOrder(order, Sort.by(Sort.Direction.DESC, sortName));
    }

    public void create(Offer offer, String nationalCode, Integer orderId) {
        Expert expert = expertService.findExpertByNationalCode(nationalCode);
        if (expert == null) throw new LogicErrorException("expert offer not found");
        Order order = orderService.findOrderById(orderId);
        if (order == null) throw new LogicErrorException("order offer not found");
        offer.setExpert(expert);
        offer.setOrder(order);
        offer.setId(null);
        if (offer.getOfferPrice() < offer.getOrder().getSuggestPrice() || offer.getOfferPrice() == null)
            throw new LogicErrorException("offer price must be more then suggested price");
        if (offer.getEndTime().isBefore(offer.getStartTime()))
            throw new LogicErrorException("offer end time must be after start time");
        if (offer.getStartTime().isBefore(offer.getOrder().getOrderRegisterTime()))
            throw new LogicErrorException("offer start time must be after order register time");
        offer.setRegistrationDateTime(LocalDateTime.now());
        offer.setStatus(OfferStatus.pending);
        order.setStatus(OrderStatus.suggestion);
        orderService.save(order);
        offerRepository.save(offer);
    }

    public void save(Offer offer) {
        offerRepository.save(offer);
    }
}
