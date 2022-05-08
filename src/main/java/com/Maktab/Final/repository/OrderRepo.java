package com.Maktab.Final.repository;

import com.Maktab.Final.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    public Order findOrderById(Integer id);
    public List<Order> findOrderByCustomer(Customer customer);
    public List<Order> findOrderBySubService(SubService subService);
}
