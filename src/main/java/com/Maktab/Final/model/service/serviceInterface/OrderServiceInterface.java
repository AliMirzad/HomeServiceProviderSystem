package com.Maktab.Final.model.service.serviceInterface;

import com.Maktab.Final.model.entity.Customer;
import com.Maktab.Final.model.entity.Order;
import com.Maktab.Final.model.entity.SubService;
import com.Maktab.Final.model.entity.enums.OrderStatus;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderServiceInterface {
    Order findOrderById(Integer id);

    List<Order> findOrderByCustomer(String nationalCode);

    List<Order> findOrderBySubServices(SubService subService);

    List<Order> findByDoneAndSelectedStatus();

    List<Order> findByTimeAndStatusAndSubServiceAndService(LocalDateTime startTime, LocalDateTime endTime, OrderStatus status, String subService, String serviceName);
}