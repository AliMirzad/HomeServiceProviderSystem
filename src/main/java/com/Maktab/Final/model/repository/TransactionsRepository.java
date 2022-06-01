package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Integer> {
    List<Transactions> findTransactionsByWalletId(Integer id);

    List<Transactions> findTransactionsByCustomer(Customer customer);

    List<Transactions> findTransactionsByExpert(Expert expert);
}
