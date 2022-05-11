package com.Maktab.Final.service.serviceInterface;

import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.CustomerPage;
import com.Maktab.Final.entity.CustomerSearchCriteria;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

public interface CustomerServInt {
    public Customer findCustomerById(Integer id);
    public Customer findCustomerByNationalCodeAndPassword(String nationalCode, String password);
    public List<Customer> findByFirstNameAndLastNameAndNationalCodeAndRegisterTimeDate(String firstName, String lastName, String nationalCode, String email);
    public Page<Customer> getCustomers(CustomerPage customerPage, CustomerSearchCriteria customerSearchCriteria);
}
