package com.Maktab.Final.repository;

import com.Maktab.Final.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
    public Customer findCustomerById(Integer id);
    public Customer findCustomerByNationalCodeAndPassword(String nationalCode, String password);
}
