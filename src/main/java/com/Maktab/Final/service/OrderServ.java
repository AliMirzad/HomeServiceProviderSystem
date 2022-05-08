package com.Maktab.Final.service;

import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Order;
import com.Maktab.Final.entity.SubService;
import com.Maktab.Final.repository.OrderRepo;
import com.Maktab.Final.service.serviceInterface.OrderServInt;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServ implements OrderServInt {
    //---------------------------------------------------------needed
    private final OrderRepo orderRepo;

    public OrderServ(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    //---------------------------------------------------------methods
    @Override
    public Order findOrderById(Integer id) {
        return orderRepo.findOrderById(id);
    }

    @Override
    public List<Order> findOrderByCustomer(Customer customer) {
        return orderRepo.findOrderByCustomer(customer);
    }

    @Override
    public List<Order> findOrderBySubService(SubService subService) {
        return findOrderBySubService(subService);
    }

    @Transactional
    public void save(Order order) {
        orderRepo.save(order);
    }
}
