package com.Maktab.Final.service.serviceInterface;

import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Expert;
import com.Maktab.Final.entity.Transactions;

import java.util.List;

public interface TransactionsServInt {
    public List<Transactions> findTransactionsByWalletId(Integer id);
    public List<Transactions> findTransactionsByCustomer(Customer customer);
    public List<Transactions> findTransactionsByExpert(Expert expert);
}
