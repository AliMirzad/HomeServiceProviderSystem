package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Expert;
import com.Maktab.Final.model.entity.Transactions;

import java.util.List;

public interface TransactionsServInt {
    public List<Transactions> findTransactionsByWalletId(Integer id);
    public List<Transactions> findTransactionsByCustomer(Customer customer);
    public List<Transactions> findTransactionsByExpert(Expert expert);
}
