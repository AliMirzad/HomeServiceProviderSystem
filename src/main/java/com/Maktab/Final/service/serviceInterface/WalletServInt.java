package com.Maktab.Final.service.serviceInterface;

import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.Wallet;

public interface WalletServInt {
    public Wallet findWalletById(Integer id);
    public Wallet findWalletByExpert(Expert expert);
    public Wallet findWalletByCustomer(Customer customer);
}
