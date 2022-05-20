package com.Maktab.Final.model.service;

import com.Maktab.Final.controller.dto.CustomerDTO;
import com.Maktab.Final.model.entity.*;
import com.Maktab.Final.model.entity.enums.CustomerUserStatus;
import com.Maktab.Final.model.entity.enums.OfferStatus;
import com.Maktab.Final.model.entity.enums.OrderStatus;
import com.Maktab.Final.model.repository.CustomerRepository;
import com.Maktab.Final.model.service.serviceInterface.CustomerServiceInterface;
import com.Maktab.Final.model.exception.LogicErrorException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class CustomerService implements CustomerServiceInterface {
    //--------------------------------------------------------------needed
    private final CustomerRepository customerRepository;
    private final OrderService orderService;
    private final CommentService commentService;
    private final ExpertService expertService;
    private final WalletService walletService;
    private final OfferService offerService;

    public CustomerService(CustomerRepository customerRepository, @Lazy OrderService orderService, @Lazy CommentService commentService, @Lazy ExpertService expertService, @Lazy WalletService walletService, @Lazy OfferService offerService) {
        this.customerRepository = customerRepository;
        this.orderService = orderService;
        this.commentService = commentService;
        this.expertService = expertService;
        this.walletService = walletService;
        this.offerService = offerService;
    }

    //--------------------------------------------------------------methods
    @Override
    public Customer findCustomerById(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) throw new LogicErrorException("customer not found");
        return customer;
    }

    public void create(Customer customer) {
        customer.setId(null);
        customer.setRegisterTime(LocalDateTime.now());
        customer.setStatus(CustomerUserStatus.fresh);
        customerRepository.save(customer);
    }

    public void chooseOfferForOrder(Integer offerId, Integer orderId) {
        Offer offer = offerService.findOfferById(offerId);
        if (offer == null) throw new LogicErrorException("offer not found");
        Order order = orderService.findOrderById(orderId);
        if (order == null) throw new LogicErrorException("order not found");
        order.setStatus(OrderStatus.selection);
        offer.setStatus(OfferStatus.selected);
        offer.setOrder(order);
        offerService.save(offer);
        orderService.save(order);
    }
}
