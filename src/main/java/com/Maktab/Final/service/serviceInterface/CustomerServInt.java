package com.Maktab.Final.service.serviceInterface;

import com.Maktab.Final.entity.Customer;

public interface CustomerServInt {
    public Customer findCustomerById(Integer id);
    public Customer findCustomerByNationalCodeAndPassword(String nationalCode, String password);
}
