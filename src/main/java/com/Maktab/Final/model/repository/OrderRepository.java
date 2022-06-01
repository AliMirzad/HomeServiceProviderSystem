package com.Maktab.Final.model.repository;

import com.Maktab.Final.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findOrderById(Integer id);

    List<Order> findOrderByCustomer(Customer customer);

    List<Order> findOrderBySubService(SubService subService);
}
