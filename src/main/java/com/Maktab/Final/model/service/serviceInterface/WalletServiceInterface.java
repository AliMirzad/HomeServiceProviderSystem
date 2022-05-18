package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.Wallet;

public interface WalletServiceInterface {
    public Wallet findWalletById(Integer id);
    public Wallet findWalletByExpert(Expert expert);
    public Wallet findWalletByCustomer(Customer customer);
}
