package com.Maktab.Final.service;

import com.Maktab.Final.entity.*;
import com.Maktab.Final.entity.enums.CustomerUserStatus;
import com.Maktab.Final.entity.enums.OfferStatus;
import com.Maktab.Final.entity.enums.OrderStatus;
import com.Maktab.Final.repository.CustomerCriteriaRepo;
import com.Maktab.Final.repository.CustomerRepo;
import com.Maktab.Final.service.serviceInterface.CustomerServInt;
import exception.LogicException;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServ implements CustomerServInt {
    //--------------------------------------------------------------needed
    private final CustomerRepo customerRepo;
    private final CustomerCriteriaRepo customerCriteriaRepo;
    private final OrderServ orderServ;
    private final CommentServ commentServ;
    private final ExpertServ expertServ;
    private final WalletServ walletServ;
    private final OfferServ offerServ;

    public CustomerServ(CustomerRepo customerRepo, CustomerCriteriaRepo customerCriteriaRepo,@Lazy OrderServ orderServ,@Lazy CommentServ commentServ,@Lazy ExpertServ expertServ,@Lazy WalletServ walletServ,@Lazy OfferServ offerServ) {
        this.customerRepo = customerRepo;
        this.customerCriteriaRepo = customerCriteriaRepo;
        this.orderServ = orderServ;
        this.commentServ = commentServ;
        this.expertServ = expertServ;
        this.walletServ = walletServ;
        this.offerServ = offerServ;
    }

    //--------------------------------------------------------------methods
    @Override
    public Customer findCustomerById(Integer id) {
        try {
            Customer customer = customerRepo.findCustomerById(id);
            if (customer == null) throw new LogicException("we dont have this customer");
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public Customer findCustomerByNationalCodeAndPassword(String nationalCode, String password) {
        try {
            Customer customer = customerRepo.findCustomerByNationalCodeAndPassword(nationalCode, password);
            if (customer == null) throw new LogicException("we don't have this customer");
            return customer;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Transactional
    public void createCustomer(Customer customer) {
        try {
            Customer customer1 = customerRepo.findCustomerByNationalCodeAndPassword(customer.getNationalCode(), customer.getPassword());
            if (customer1 != null) throw new LogicException("we have this customer before");
            if (customer.getEmail() == null || customer.getEmail().isEmpty())
                throw new LogicException("we cant add customer without email");
            customer.setRegisterTime(LocalDateTime.now());
            customer.setStatus(CustomerUserStatus.fresh);
            customerRepo.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Transactional
    public void updatePassword(Integer id, String newPassword) {
        try {
            Customer customer = customerRepo.findCustomerById(id);
            if (customer.getPassword().equals(newPassword)) throw new LogicException("this is your old password");
            customer.setPassword(newPassword);
            customerRepo.save(customer);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Transactional
    public void saveOrder(Integer id, Order selectedOrder) {
        try {
            Customer customer = customerRepo.findCustomerById(id);
            if (customer == null) throw new LogicException("we dont have this customer before");
            selectedOrder.setStatus(OrderStatus.new_req);
            selectedOrder.setCustomer(customer);
            selectedOrder.setOrderRegisterTime(LocalDateTime.now());
            orderServ.save(selectedOrder);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Transactional
    public void saveComment(Integer id, Comment comment) {
        try {
            Customer customer = customerRepo.findCustomerById(id);
            if (customer == null) throw new LogicException("we dont have this customer");
            Expert expert = expertServ.findExpertById(comment.getExpert().getId());
            if (expert == null) throw new LogicException("we dont have this expert");
            if (comment.getPoint()) {
                expert.setPoint(expert.getPoint() + 1);
            } else expert.setPoint(expert.getPoint() - 1);
            comment.setCustomer(customer);
            expertServ.update(expert);
            commentServ.save(comment);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Transactional
    public void saveWallet(Integer id, Wallet wallet) {
        try {
            Customer customer = customerRepo.findCustomerById(id);
            if (customer == null) throw new LogicException("we dont have this customer");
            wallet.setCustomer(customer);
            walletServ.save(wallet);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Transactional
    public void chooseOfferForOrder(Integer offerId, Integer orderId) {
        try {
            Offer offer = offerServ.findOfferById(offerId);
            if (offer == null) throw new LogicException("we dont have this offer");
            Order order = orderServ.findOrderById(orderId);
            if (order == null) throw new LogicException("we dont have this order");
            order.setStatus(OrderStatus.selection);
            offer.setStatus(OfferStatus.selected);
            offerServ.save(offer);
            orderServ.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

    @Override
    public Page<Customer> getCustomers(CustomerPage customerPage, CustomerSearchCriteria customerSearchCriteria) {
        return customerCriteriaRepo.findAllWithFilters(customerPage, customerSearchCriteria);
    }
}
