package com.Maktab.Final.repository;

import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepo extends JpaRepository<Wallet, Integer> {
    public Wallet findWalletById(Integer id);
    public Wallet findWalletByExpert(Expert expert);
    public Wallet findWalletByCustomer(Customer customer);
}
