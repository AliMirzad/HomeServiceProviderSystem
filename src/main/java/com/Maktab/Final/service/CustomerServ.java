package com.Maktab.Final.service;

import com.Maktab.Final.entity.*;
import com.Maktab.Final.repository.CustomerRepo;
import com.Maktab.Final.service.serviceInterface.CustomerServInt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerServ implements CustomerServInt {
    //--------------------------------------------------------------needed
    private final CustomerRepo customerRepo;
    private final OrderServ orderServ;
    private final CommentServ commentServ;
    private final ExpertServ expertServ;
    private final WalletServ walletServ;

    public CustomerServ(CustomerRepo customerRepo, OrderServ orderServ, CommentServ commentServ, ExpertServ expertServ, WalletServ walletServ) {
        this.customerRepo = customerRepo;
        this.orderServ = orderServ;
        this.commentServ = commentServ;
        this.expertServ = expertServ;
        this.walletServ = walletServ;
    }

    //--------------------------------------------------------------methods
    @Override
    public Customer findCustomerById(Integer id) {
        return customerRepo.findCustomerById(id);
    }

    @Override
    public Customer findCustomerByNationalCodeAndPassword(String nationalCode, String password) {
        return customerRepo.findCustomerByNationalCodeAndPassword(nationalCode, password);
    }

    @Transactional
    public void createCustomer(Customer customer) {
        customerRepo.save(customer);
    }

    @Transactional
    public void updatePassword(Integer id, String newPassword) {
        Customer customer = customerRepo.findCustomerById(id);
        customer.setPassword(newPassword);
        customerRepo.save(customer);
    }

    @Transactional
    public void saveOrder(Integer id, Order selectedOrder) {
        Customer customer = customerRepo.findCustomerById(id);
        selectedOrder.setCustomer(customer);
        orderServ.save(selectedOrder);
    }

    @Transactional
    public void saveComment(Integer id, Comment comment) {
        Customer customer = customerRepo.findCustomerById(id);
        Expert expert = expertServ.findExpertById(comment.getExpert().getId());
        if (comment.getPoint()) {
            expert.setPoint(expert.getPoint() + 1);
        } else expert.setPoint(expert.getPoint() - 1);
        comment.setCustomer(customer);
        expertServ.update(expert);
        commentServ.save(comment);
    }

    @Transactional
    public void saveWallet(Integer id, Wallet wallet) {
        Customer customer = customerRepo.findCustomerById(id);
        wallet.setCustomer(customer);
        walletServ.save(wallet);
    }

}
