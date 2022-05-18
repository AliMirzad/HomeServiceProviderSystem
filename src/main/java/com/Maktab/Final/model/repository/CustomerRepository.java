package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Customer findCustomerById(Integer id);
    public Customer findCustomerByNationalCodeAndPassword(String nationalCode, String password);
}
