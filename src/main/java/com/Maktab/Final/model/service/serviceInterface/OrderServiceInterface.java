package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Order;
import com.Maktab.Final.model.entity.SubService;

import java.util.List;

public interface OrderServiceInterface {
    public Order findOrderById(Integer id);
    public List<Order> findOrderByCustomer(Customer customer);
    public List<Order> findOrderBySubServices(SubService subService);
}
