package com.Maktab.Final.repository;

import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionsRepo extends JpaRepository<Transactions, Integer> {
    public List<Transactions> findTransactionsByWalletId(Integer id);
    public List<Transactions> findTransactionsByCustomer(Customer customer);
    public List<Transactions> findTransactionsByExpert(Expert expert);
}
