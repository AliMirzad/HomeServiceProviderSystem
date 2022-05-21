package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Customer;

public interface CustomerServiceInterface {
    Customer findCustomerById(Integer id);

    Customer findCustomerByNationalCode(String nationalCode);
}
