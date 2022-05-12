package com.Maktab.Final.service;

import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.Wallet;
import com.Maktab.Final.repository.WalletRepo;
import com.Maktab.Final.service.serviceInterface.WalletServInt;
import exception.LogicException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WalletServ implements WalletServInt {
    //--------------------------------------------------------------needed
    private final WalletRepo walletRepo;
    private final ExpertServ expertServ;
    private final CustomerServ customerServ;

    public WalletServ(WalletRepo walletRepo, @Lazy ExpertServ expertServ, @Lazy CustomerServ customerServ) {
        this.walletRepo = walletRepo;
        this.expertServ = expertServ;
        this.customerServ = customerServ;
    }

    //--------------------------------------------------------------methods
    @Override
    public Wallet findWalletById(Integer id) {
        try {
            Wallet wallet = walletRepo.findWalletById(id);
            if (wallet == null) throw new LogicException("we need valid wallet");
            return wallet;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public Wallet findWalletByExpert(Expert expert) {
        try {
            Expert expert1 = expertServ.findExpertById(expert.getId());
            if (expert1 == null) throw new LogicException("we need valid expert");
            Wallet wallet = walletRepo.findWalletByExpert(expert);
            if (wallet == null) throw new LogicException("we need valid wallet");
            return wallet;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public Wallet findWalletByCustomer(Customer customer) {
        try {
            Customer customer1 = customerServ.findCustomerById(customer.getId());
            if (customer1 == null) throw new LogicException("we need valid customer");
            Wallet wallet = walletRepo.findWalletByCustomer(customer);
            if (wallet == null) throw new LogicException("we need valid wallet");
            return wallet;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Transactional
    public void save(Wallet wallet) {
        try {
            if (wallet.getBalance() == null) throw new LogicException("we need balance for wallet");
            if (wallet.getExpert() != null && wallet.getCustomer() != null)
                throw new LogicException("wallet must be for customer or expert");
            walletRepo.save(wallet);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }
}
