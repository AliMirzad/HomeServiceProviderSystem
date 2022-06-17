package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.*;
import com.Maktab.Final.model.entity.enums.CustomerUserStatus;
import com.Maktab.Final.model.entity.enums.OfferStatus;
import com.Maktab.Final.model.entity.enums.OrderStatus;
import com.Maktab.Final.model.entity.enums.Role;
import com.Maktab.Final.model.repository.CustomerRepository;
import com.Maktab.Final.model.service.serviceInterface.CustomerServiceInterface;
import com.Maktab.Final.model.exception.LogicErrorException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class CustomerService implements CustomerServiceInterface {
    private final CustomerRepository customerRepository;
    private final OrderService orderService;
    private final CommentService commentService;
    private final ExpertService expertService;
    private final WalletService walletService;
    private final OfferService offerService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerService(CustomerRepository customerRepository, @Lazy OrderService orderService, @Lazy CommentService commentService, @Lazy ExpertService expertService, @Lazy WalletService walletService, @Lazy OfferService offerService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.customerRepository = customerRepository;
        this.orderService = orderService;
        this.commentService = commentService;
        this.expertService = expertService;
        this.walletService = walletService;
        this.offerService = offerService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Customer findCustomerById(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if (customer == null) throw new LogicErrorException("customer not found");
        return customer;
    }

    @Override
    public Customer findCustomerByNationalCode(String nationalCode) {
        Customer customer = customerRepository.findCustomerByNationalCode(nationalCode);
        if (customer == null) throw new LogicErrorException("customer not found");
        return customer;
    }

    public void create(Customer customer) {
        customer.setRole(Role.ROLE_CUSTOMER);
        customer.setRegisterTime(LocalDateTime.now());
        customer.setStatus(CustomerUserStatus.fresh);
        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
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

    public void payForJobWallet(Integer offerId) {
        Offer offer = offerService.findOfferById(offerId);
        if (!offer.getOrder().getStatus().equals(OrderStatus.done))
            throw new LogicErrorException("order is not done yet");
        Wallet customerWallet = walletService.findWalletByCustomer(offer.getOrder().getCustomer().getNationalCode());
        Wallet expertWallet = walletService.findWalletByExpert(offer.getExpert().getNationalCode());
        if (customerWallet.getBalance() < offer.getOfferPrice())
            throw new LogicErrorException("you dont have balance for wallet pay");
        customerWallet.setBalance(customerWallet.getBalance() - offer.getOfferPrice());
        expertWallet.setBalance(expertWallet.getBalance() + (offer.getOfferPrice() - (offer.getOfferPrice() * 0.7)));
        walletService.save(customerWallet);
        walletService.save(expertWallet);
        offer.setStatus(OfferStatus.finished);
        Order order = offer.getOrder();
        order.setStatus(OrderStatus.paid);
        offerService.save(offer);
        orderService.save(order);
    }

    public void payForJobOnline(Integer offerId ) {
        Offer offer = offerService.findOfferById(offerId);
        if (!offer.getOrder().getStatus().equals(OrderStatus.done))
            throw new LogicErrorException("order is not done yet");
        Wallet expertWallet = walletService.findWalletByExpert(offer.getExpert().getNationalCode());
        expertWallet.setBalance(expertWallet.getBalance() + (offer.getOfferPrice() - (offer.getOfferPrice() * 0.7)));
        offer.setStatus(OfferStatus.finished);
        Order order = offer.getOrder();
        order.setStatus(OrderStatus.paid);
        offerService.save(offer);
        orderService.save(order);
        walletService.save(expertWallet);
    }
}
