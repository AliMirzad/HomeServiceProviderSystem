package com.Maktab.Final.service;

import com.Maktab.Final.entity.Customer;
import com.Maktab.Final.entity.Order;
import com.Maktab.Final.entity.SubService;
import com.Maktab.Final.repository.OrderRepo;
import com.Maktab.Final.service.serviceInterface.OrderServInt;
import exception.LogicException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServ implements OrderServInt {
    //---------------------------------------------------------needed
    private final OrderRepo orderRepo;
    private final CustomerServ customerServ;
    private final SubServiceServ subServiceServ;

    public OrderServ(OrderRepo orderRepo, @Lazy CustomerServ customerServ, SubServiceServ subServiceServ) {
        this.orderRepo = orderRepo;
        this.customerServ = customerServ;
        this.subServiceServ = subServiceServ;
    }

    //---------------------------------------------------------methods
    @Override
    public Order findOrderById(Integer id) {
        try {
            Order order = orderRepo.findOrderById(id);
            if (order == null) throw new LogicException("we dont have this order");
            return order;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public List<Order> findOrderByCustomer(Customer customer) {
        try {
            Customer customer1 = customerServ.findCustomerById(customer.getId());
            if (customer1 == null) throw new LogicException("unValid customer");
            List<Order> orders = orderRepo.findOrderByCustomer(customer);
            if (orders == null) throw new LogicException("order list is null");
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Override
    public List<Order> findOrderBySubServices(SubService subService) {
        try {
            SubService subService1 = subServiceServ.findSubServiceById(subService.getId());
            if (subService1 == null) throw new LogicException("we dont have this subService");
            List<Order> orders = orderRepo.findOrderBySubService(subService);
            if (orders == null) throw new LogicException("order list is null");
            return orders;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
            return null;
        }
    }

    @Transactional
    public void save(Order order) {
        try {
            if (order.getAddress() == null || order.getAddress().isEmpty()) throw new LogicException("we need address for order");
            if (order.getDescription() == null || order.getDescription().isEmpty()) throw new LogicException("we need description for order");
            if (order.getSuggestPrice() == null) throw new LogicException("we need suggestion for price");
            if (order.getSubService() == null) throw new LogicException("we need sub service for organize order");
            orderRepo.save(order);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("-----------------------------------------------------");
            System.out.println(e.getMessage());
            System.out.println("-----------------------------------------------------");
        }
    }

}
