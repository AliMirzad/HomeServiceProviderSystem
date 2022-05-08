package com.Maktab.Final.service;

import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.Wallet;
import com.Maktab.Final.repository.WalletRepo;
import com.Maktab.Final.service.serviceInterface.WalletServInt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WalletServ implements WalletServInt {
    //--------------------------------------------------------------needed
    private WalletRepo walletRepo;

    public WalletServ(WalletRepo walletRepo) {
        this.walletRepo = walletRepo;
    }
    //--------------------------------------------------------------methods
    @Override
    public Wallet findWalletById(Integer id) {
        return walletRepo.findWalletById(id);
    }

    @Override
    public Wallet findWalletByExpert(Expert expert) {
        return walletRepo.findWalletByExpert(expert);
    }

    @Override
    public Wallet findWalletByCustomer(Customer customer) {
        return walletRepo.findWalletByCustomer(customer);
    }

    @Transactional
    public void save(Wallet wallet) {
        walletRepo.save(wallet);
    }
}
