package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
    Wallet findWalletById(Integer id);

    Wallet findWalletByExpert(Expert expert);

    Wallet findWalletByCustomer(Customer customer);
}
