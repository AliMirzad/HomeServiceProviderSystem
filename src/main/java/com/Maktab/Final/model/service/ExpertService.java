package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.*;
import com.Maktab.Final.model.entity.enums.ExpertUserStatus;
import com.Maktab.Final.model.entity.enums.OfferStatus;
import com.Maktab.Final.model.entity.enums.OrderStatus;
import com.Maktab.Final.model.entity.enums.Role;
import com.Maktab.Final.model.entity.queryEntity.ServiceOrderQ;
import com.Maktab.Final.model.repository.ExpertRepository;
import com.Maktab.Final.model.service.serviceInterface.ExpertServiceInterface;
import com.Maktab.Final.model.exception.LogicErrorException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class ExpertService implements ExpertServiceInterface {
    //---------------------------------------------------------needed
    private final ExpertRepository expertRepository;
    private final OfferService offerService;
    private final OrderService orderService;

    public ExpertService(ExpertRepository expertRepository, @Lazy OfferService offerService, @Lazy OrderService orderService) {
        this.expertRepository = expertRepository;
        this.offerService = offerService;
        this.orderService = orderService;
    }

    @Override
    public Expert findExpertById(Integer id) {
        Expert expert = expertRepository.findExpertById(id);
        if (expert == null) throw new LogicErrorException("expert not found");
        return expert;
    }

    @Override
    public Expert findExpertByNationalCode(String nationalCode) {
        Expert expert = expertRepository.findExpertByNationalCode(nationalCode);
        if (expert == null) throw new LogicErrorException("expert not found");
        return expert;
    }

    @Override
    public List<ServiceOrderQ> findExpertServiceOrders(String nationalCode) {
        List<ServiceOrderQ> serviceOrderQList = expertRepository.findExpertServiceOrders(nationalCode);
        if (serviceOrderQList == null) throw new LogicErrorException("order list is empty");
        return serviceOrderQList;
    }

    @Override
    public List<Expert> findServiceExperts(String serviceName) {
        List<Expert> experts = expertRepository.findServiceExperts(serviceName);
        if (experts == null) throw new LogicErrorException("expert list is empty");
        return experts;
    }

    public void create(Expert expert) {
        expert.setId(null);
        expert.setRole(Role.ROLE_EXPERT);
        expert.setRegisterTime(LocalDateTime.now());
        expert.setPoint(100);
        expert.setStatus(ExpertUserStatus.waiting);
        expertRepository.save(expert);
    }

    public void finishJob(Integer offerId) {
        Offer offer = offerService.findOfferById(offerId);
        if (!offer.getStatus().equals(OfferStatus.selected)) {
            throw new LogicErrorException("this order is not accepted");
        }
        if (LocalDateTime.now().isBefore(offer.getStartTime())) {
            throw new LogicErrorException("we don't reach start time");
        }
        offer.setStatus(OfferStatus.finished);
        Order order = orderService.findOrderById(offer.getOrder().getId());
        order.setStatus(OrderStatus.done);
        offerService.save(offer);
        orderService.save(order);
    }
}
