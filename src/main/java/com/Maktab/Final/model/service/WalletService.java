package com.Maktab.Final.model.service;

import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.Wallet;
import com.Maktab.Final.model.repository.WalletRepository;
import com.Maktab.Final.model.service.serviceInterface.WalletServiceInterface;
import com.Maktab.Final.model.exception.LogicErrorException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class WalletService implements WalletServiceInterface {
    private final WalletRepository walletRepository;
    private final ExpertService expertService;
    private final CustomerService customerService;

    public WalletService(WalletRepository walletRepository, @Lazy ExpertService expertService, @Lazy CustomerService customerService) {
        this.walletRepository = walletRepository;
        this.expertService = expertService;
        this.customerService = customerService;
    }

    @Override
    public Wallet findWalletById(Integer id) {
        Wallet wallet = walletRepository.findWalletById(id);
        if (wallet == null) throw new LogicErrorException("wallet not found");
        return wallet;
    }

    @Override
    public Wallet findWalletByExpert(String expertNationalCode) {
        Expert expert = expertService.findExpertByNationalCode(expertNationalCode);
        if (expert == null)
            throw new LogicErrorException("expert wallet not found");
        Wallet wallet = walletRepository.findWalletByExpert(expert);
        if (wallet == null) throw new LogicErrorException("expert wallet not found");
        return wallet;
    }

    @Override
    public Wallet findWalletByCustomer(String customerNationalCode) {
        Customer customer = customerService.findCustomerByNationalCode(customerNationalCode);
        if (customer == null)
            throw new LogicErrorException("customer wallet not found");
        Wallet wallet = walletRepository.findWalletByCustomer(customer);
        if (wallet == null) throw new LogicErrorException("customer wallet not found");
        return wallet;
    }

    public void create(Wallet wallet) {
        if (wallet.getBalance() == null) throw new LogicErrorException("balance wallet can't be null");
        if (wallet.getExpert() != null && wallet.getCustomer() != null)
            throw new LogicErrorException("wallet owner must be customer or expert");
        walletRepository.save(wallet);
    }

    public void save(Wallet wallet) {
        walletRepository.save(wallet);
    }
}
