package com.Maktab.Final.service.serviceInterface;

import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Order;
import com.Maktab.Final.entity.SubService;

import java.util.List;

public interface OrderServInt {
    public Order findOrderById(Integer id);
    public List<Order> findOrderByCustomer(Customer customer);
    public List<Order> findOrderBySubServices(SubService subService);
}
