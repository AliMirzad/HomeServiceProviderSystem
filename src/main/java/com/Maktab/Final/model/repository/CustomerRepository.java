package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerById(Integer id);

    Customer findCustomerByNationalCode(String nationalCode);
}
